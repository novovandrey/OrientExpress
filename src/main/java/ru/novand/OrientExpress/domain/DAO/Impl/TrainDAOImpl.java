package ru.novand.OrientExpress.domain.DAO.Impl;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Train;
import ru.novand.OrientExpress.exception.CustomSQLException;


@Repository("trainDao")
public class TrainDAOImpl implements TrainDAO {

    @PersistenceContext
    private EntityManager manager;

    public TrainDAOImpl() {
    }

    @Override
    public Train save(Train entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save train: " + ex);
        }
        return entity;
    }

    @Override
    public List<Train> findAll() {
        List<Train> result = null;
        TypedQuery<Train> query = manager.createNamedQuery("Train.findAll", Train.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public Train delete(Train entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove train: " + ex);
        }
        return entity;
    }

    @Override
    public Train update(Train entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update train: " + ex);
        }
        return entity;
    }

    @Override
    public Train findById(int id) {
        Train result = manager.find(Train.class, id);
        return result;
    }

    @Override
    public Train findByCode(String code) {

        TypedQuery<Train> query = manager.createNamedQuery("Train.findByCode", Train.class);
        query.setParameter("trainCode", code);
        Train result = query.getSingleResult();
        return result;

    }

}
