package ru.novand.OrientExpress.domain.DAO.Impl;

import java.util.List;
import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.exception.CustomSQLException;


@Repository("stationDAO")
public class StationDAOImpl implements StationDAO {

    @PersistenceContext
    private EntityManager manager;

    public StationDAOImpl() {
    }

    @Override
    @Transactional
    public Station save(Station entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save station: " + ex);
        }
        return entity;
    }

    @Override

    public List<Station> findAll() {
        TypedQuery<Station> query = manager.createNamedQuery("Station.GetAllStationList", Station.class);
        List<Station> result = query.getResultList();
        return result;
    }

    @Override
    public Station delete(Station entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove station: " + ex);
        }
        return entity;
    }

    @Override
    public Station update(Station entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update station: " + ex);
        }
        return entity;
    }

    public Station findById(int id) {
        Station result = manager.find(Station.class, id);
        return result;
    }

    @Override
    public Station getStation(String stationName) {
        Station result;
        try {
            TypedQuery<Station> query = manager.createNamedQuery("Station.GetStationByName", Station.class);
            query.setParameter("stationName", stationName);
            result = query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

}
