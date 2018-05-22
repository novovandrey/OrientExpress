package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.TrainRoute;

public interface TrainRouteDAO extends GenDAO<TrainRoute>  {
    public TrainRoute findByTrainCode(String traincode);
}
