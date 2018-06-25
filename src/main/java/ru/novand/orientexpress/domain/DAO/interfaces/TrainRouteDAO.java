package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.TrainRoute;

public interface TrainRouteDAO extends GenDAO<TrainRoute>  {
    public TrainRoute findByTrainCode(String traincode);
}
