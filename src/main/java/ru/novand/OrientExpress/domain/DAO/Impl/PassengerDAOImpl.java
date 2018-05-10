package ru.novand.OrientExpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository("passengerDao")
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

    @Override
    public List<Passenger> findAll() {
        return null;
    }

    @Override
    public Passenger delete(Passenger entity){
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
    public Passenger findById(int id) {
        Passenger result = manager.find(Passenger.class, id);
        return result;
    }
}
