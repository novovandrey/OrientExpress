package ru.novand.orientexpress.domain.DAO.Impl;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.exception.CustomSQLException;


@Repository("trainDAO")
public class TrainDAOImpl implements TrainDAO {

    @PersistenceContext
    private EntityManager manager;

    public TrainDAOImpl() {
    }


    @Override
    @Transactional
    public Train save(Train entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save train: " + ex);
        }
        return entity;
    }

    public List<Train> findAll() {
        List<Train> result = null;
        TypedQuery<Train> query = manager.createNamedQuery("Train.findAll", Train.class);
        result = query.getResultList();
        return result;
    }

    public Train delete(Train entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove train: " + ex);
        }
        return entity;
    }

    public Train update(Train entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update train: " + ex);
        }
        return entity;
    }

    public Train findById(int id) {
        Train result = manager.find(Train.class, id);
        return result;
    }

    public Train findByCode(String code) {

        TypedQuery<Train> query = manager.createNamedQuery("Train.findByCode", Train.class);
        query.setParameter("trainCode", code);
        Train result = query.getSingleResult();
        return result;

    }

}
