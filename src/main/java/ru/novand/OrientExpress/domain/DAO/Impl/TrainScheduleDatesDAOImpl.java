package ru.novand.OrientExpress.domain.DAO.Impl;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.OrientExpress.domain.entities.TrainScheduleDates;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("trainScheduleDatesDAO")
public class TrainScheduleDatesDAOImpl implements TrainScheduleDatesDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public TrainScheduleDates save(TrainScheduleDates entity) {

        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save TrainScheduleDates: " + ex);
        }
        return entity;

    }

    @Override
    public List<TrainScheduleDates> findAll() {
        List<TrainScheduleDates> result = null;
        TypedQuery<TrainScheduleDates> query = manager.createNamedQuery("TrainScheduleDates.findAll", TrainScheduleDates.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public TrainScheduleDates delete(TrainScheduleDates entity) {
        return null;
    }

    @Override
    public TrainScheduleDates update(TrainScheduleDates entity) {
        return null;
    }

    @Override
    public TrainScheduleDates findById(int id) {
        return null;
    }
}
