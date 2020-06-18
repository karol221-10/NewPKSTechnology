package pl.kompikownia.pksmanager.ticketmanager.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.DiscountProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.projection.TicketProjection;
import pl.kompikownia.pksmanager.ticketmanager.business.repository.TicketRepository;
import pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity.DiscountEntity;
import pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity.TicketEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity.QDiscountEntity.discountEntity;
import static pl.kompikownia.pksmanager.ticketmanager.infrastructure.entity.QTicketEntity.ticketEntity;

@AllArgsConstructor
@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private EntityManager entityManager;

    @Override
    public List<DiscountProjection> getAllAvailableDiscounts() {
        JPAQuery<DiscountEntity> query = new JPAQuery<>(entityManager);

        return query.from(discountEntity)
                .where(discountEntity.isActive.eq(true))
                .fetch()
                .stream()
                .map(DiscountEntity::toProjection)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketProjection> getTicketsForUser(String userId) {
        JPAQuery<TicketEntity> query = new JPAQuery<>(entityManager);

        return query.from(ticketEntity)
                .where(ticketEntity.userId.eq(userId))
                .fetch()
                .stream()
                .map(TicketEntity::toProjection)
                .collect(Collectors.toList());
    }
}
