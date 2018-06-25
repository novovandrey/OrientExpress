package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.orientexpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("passengerDAO")
public class PassengerDAOImpl implements PassengerDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Passenger save(Passenger passenger) {
        try {
            manager.persist(passenger);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save station: " + ex);

        }
        return passenger;
    }

    public PassengerDAOImpl() {
    }

    @Override
    public List<Passenger> findAll() {
        return null;
    }

    @Override
    public Passenger delete(Passenger entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove station: " + ex);
        }
        return entity;

    }

    @Override
    public Passenger update(Passenger entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update station: " + ex);
        }
        return entity;


    }

    @Override
    public Passenger findByUserName(String username) {
        TypedQuery<Passenger> query = manager.createNamedQuery("Passenger.findbyUserName", Passenger.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public Passenger findById(int id) {
        return null;
    }

    @Override
    public Passenger findByUserId(int userid) {
        return null;
    }
}
