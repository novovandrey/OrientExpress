package ru.novand.OrientExpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.TrainRoute;
import ru.novand.OrientExpress.domain.entities.TrainScheduleDates;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("trainRouteDAO")
public class TrainRouteDAOImpl implements TrainRouteDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public TrainRoute save(TrainRoute entity) {

        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save TrainScheduleDates: " + ex);
        }
        return entity;

    }

    @Override
    public List<TrainRoute> findAll() {
        List<TrainRoute> result = null;
        TypedQuery<TrainRoute> query = manager.createNamedQuery("TrainRoute.findAll", TrainRoute.class);
        result = query.getResultList();
        return result;
    }

    @Override
    @Transactional
    public TrainRoute delete(TrainRoute entity) {
        try {
            TrainRoute trainRoute = manager.find(TrainRoute.class, entity.getIdTrainRoute());
            manager.remove(trainRoute);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove TrainRoute: " + ex);
        }
        return entity;
    }

    @Transactional
    @Override
    public TrainRoute update(TrainRoute entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update TrainRoute: " + ex);
        }
        return entity;
    }

    @Override
    @Transactional
    public TrainRoute findById(int id) {
        return manager.find(TrainRoute.class, id);
    }

    @Override
    @Transactional
    public TrainRoute findByTrainCode(String traincode) {
        TrainRoute result = null;
        TypedQuery<TrainRoute> query = manager.createNamedQuery("TrainRoute.findByTrainCode", TrainRoute.class);
        query.setParameter("traincode",traincode);
        result = query.getSingleResult();
        return result;
    }
}
