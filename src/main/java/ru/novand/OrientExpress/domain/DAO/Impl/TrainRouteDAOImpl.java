package ru.novand.OrientExpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.OrientExpress.domain.entities.TrainRoute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("trainRouteDAO")
public class TrainRouteDAOImpl implements TrainRouteDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public TrainRoute save(TrainRoute entity) {
        return null;
    }

    @Override
    public List<TrainRoute> findAll() {
        List<TrainRoute> result = null;
        TypedQuery<TrainRoute> query = manager.createNamedQuery("TrainRoute.findAll", TrainRoute.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public TrainRoute delete(TrainRoute entity) {
        return null;
    }

    @Override
    public TrainRoute update(TrainRoute entity) {
        return null;
    }

    @Override
    public TrainRoute findById(int id) {
        return null;
    }
}
