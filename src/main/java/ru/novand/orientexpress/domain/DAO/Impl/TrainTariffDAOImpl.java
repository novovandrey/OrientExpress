package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainTariffDAO;
import ru.novand.orientexpress.domain.entities.TrainTariff;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("trainTariffDAO")
public class TrainTariffDAOImpl implements TrainTariffDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public TrainTariff save(TrainTariff entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save TrainScheduleDates: " + ex);
        }
        return entity;
    }

    @Override
    public List<TrainTariff> findAll() {
        List<TrainTariff> result = null;
        TypedQuery<TrainTariff> query = manager.createNamedQuery("TrainTariff.findAll", TrainTariff.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public List<TrainTariff> getAllTrainTariffByTrainCode(String traincode) {
        List<TrainTariff> result = null;
        TypedQuery<TrainTariff> query = manager.createNamedQuery("TrainTariff.findByTrainCode", TrainTariff.class);
        query.setParameter("traincode", traincode);
        result = query.getResultList();
        return result;
    }

    @Override
    public TrainTariff delete(TrainTariff entity) {
        return null;
    }

    @Override
    public TrainTariff update(TrainTariff entity) {
        return null;
    }

    @Override
    public TrainTariff findById(int id) {
        return null;
    }
}
