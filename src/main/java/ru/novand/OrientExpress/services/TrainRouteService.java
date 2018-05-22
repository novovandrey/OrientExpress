package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.OrientExpress.domain.dto.TrainRouteDTO;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Train;
import ru.novand.OrientExpress.domain.entities.TrainRoute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TrainRouteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TrainRouteDAO trainRouteDAO;

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainDAO trainDAO;

    public TrainRoute addTrainRoute(String traincode,String depstationname, String arrstationname) {

        List<Schedule> schedules = new ArrayList<>();
        TrainRoute trainRoute = new TrainRoute();
        return trainRouteDAO.save(trainRoute);
    }

    public List<TrainRoute> getAllTrainRoute() {
        List<TrainRoute> list = trainRouteDAO.findAll();

        List<Schedule> scheduleList = new ArrayList<>();

        for ( TrainRoute trainRoute:list ) {
            for ( Schedule schedule:trainRoute.getScheduleList() ) {
                if (schedule.getInterval()!=0)
                    scheduleList.add(schedule);
            }
            scheduleList.sort((a, b) -> Integer.compare(a.getInterval(), b.getInterval()));
            trainRoute.setScheduleList(scheduleList);
            scheduleList = new ArrayList<>();
        }

        return list;
    }

    public List<Train> getAllTrainsInTrainRoute(List<Train> trains) {
        List<TrainRoute> list = trainRouteDAO.findAll();
        List<Train> trainsFromRoute = new ArrayList<>();
        List<Train> trainList = new ArrayList<>();

        for (TrainRoute trainRoute:list) {
            trainsFromRoute.add(trainRoute.getTrain());
        }

        for (Train train: trains) {
            if (!trainsFromRoute.contains(train)){
                trainList.add(train);
            }
        }

        return trainList;
    }

    public void update(String arrst,String depst,String traincode, int id) {

        TrainRoute trainRoute = trainRouteDAO.findById(id);
        Train train = trainDAO.findByCode(traincode);
        trainRoute.setTrain(train);

        Station station = stationDAO.getStation(arrst);
        trainRoute.setArrivalstation(station);
        station = stationDAO.getStation(depst);
        trainRoute.setDeparturestation(station);
        trainRouteDAO.update(trainRoute);
    }

    public TrainRoute save(String arrst,String depst,String traincode) {

        TrainRoute trainRoute = new TrainRoute();
        Train train = trainDAO.findByCode(traincode);
        trainRoute.setTrain(train);
        Station station = stationDAO.getStation(arrst);
        trainRoute.setArrivalstation(station);
        station = stationDAO.getStation(depst);
        trainRoute.setDeparturestation(station);
        TrainRoute trainRouteResult = trainRouteDAO.save(trainRoute);
        return trainRouteResult;
    }

    public void delete(String traincode) {
        TrainRoute trainRoute = trainRouteDAO.findByTrainCode(traincode);
        if (trainRoute!=null) trainRouteDAO.delete(trainRoute);
    }
}