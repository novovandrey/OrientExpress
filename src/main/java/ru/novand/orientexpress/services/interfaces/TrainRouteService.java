package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainRoute;

import java.util.List;

public interface TrainRouteService {

    public TrainRoute addTrainRoute(String traincode,String depstationname, String arrstationname) ;

    public List<TrainRoute> getAllTrainRoute();

    public List<Train> getAllTrainsInTrainRoute();

    public void update(String arrst,String depst,String traincode, int id);

    public TrainRoute save(String arrst,String depst,String traincode);

    public void delete(String traincode);

    public List<Station> getAllStations();
}