package pl.kompikownia.pksmanager.security.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.kompikownia.pksmanager.security.business.repository.RoleRepository;
import pl.kompikownia.pksmanager.security.infrastructure.entity.RoleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static pl.kompikownia.pksmanager.security.infrastructure.entity.QRoleEntity.roleEntity;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getRoleIdByName(String roleName) {
        val query = new JPAQuery<RoleEntity>(entityManager);
        val role = query.from(roleEntity)
                .where(roleEntity.roleName.eq(roleName))
                .fetchFirst();
        if(role == null) {
            throw new IllegalArgumentException(roleName + " role does not exists!");
        }
        return role.getId();
    }
}
