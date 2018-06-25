package ru.novand.orientexpress.domain.DAO.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.RoleDAO;
import ru.novand.orientexpress.domain.entities.Role;

import javax.persistence.*;
import java.util.List;

@Repository("roleDAO")
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public List<Role> findAll() {
        List<Role> result = null;
        TypedQuery<Role> query = manager.createNamedQuery("Role.findAll", Role.class);
        try {
            result = (List<Role>) query.getResultList();
        }
        catch (NoResultException nre){
            logger.info(nre.toString());
        }
        return result;
    }

    @Override
    public void save(Role entity) {

        Query query = manager.createNativeQuery(  " insert into authorities\n" +
                "values(?,'ROLE_USER') ");
        query.setParameter(1, entity.getName());
        query.executeUpdate();

    }
}
