package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.User;

import javax.persistence.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.novand.orientexpress.exception.CustomSQLException;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User save(User user) {

        try {
            manager.persist(user);
        } catch (PersistenceException ex) {
            ex.printStackTrace();

        }
        return user;

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User delete(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByUsername(String name) {
        TypedQuery<User> query = manager.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", name);
        User result = null;
        try {
            result = (User) query.getSingleResult();
        }
        catch (NoResultException nre){
            logger.info(nre.toString());
        }

        return result;
    }
}
