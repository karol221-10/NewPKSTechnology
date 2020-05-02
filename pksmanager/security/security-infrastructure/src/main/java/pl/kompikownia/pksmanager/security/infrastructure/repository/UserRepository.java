package pl.kompikownia.pksmanager.security.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.kompikownia.pksmanager.security.infrastructure.entity.UserEntity;
import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;
import pl.kompikownia.pksmanager.security.infrastructure.repository.port.UserAuthenticationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static pl.kompikownia.pksmanager.security.infrastructure.entity.QUserEntity.userEntity;

@Service
public class UserRepository implements UserAuthenticationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserDetailsModel findByUsername(String username) {
        JPAQuery<UserEntity> query = new JPAQuery<>(entityManager);
        val entity = query.from(userEntity).where(userEntity.username.eq(username)).fetchFirst();
        return UserDetailsModel.of(entity);
    }
}
