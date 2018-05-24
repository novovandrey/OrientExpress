package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User save(User entity) {
        return null;
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
    public User findByName(String name) {
        TypedQuery<User> query = manager.createNamedQuery("User.findByName", User.class);
        query.setParameter("username", name);
        User result = query.getSingleResult();
        return result;
    }
}
