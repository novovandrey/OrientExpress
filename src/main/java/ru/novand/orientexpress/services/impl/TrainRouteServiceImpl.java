package ru.novand.orientexpress.services.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.ScheduleDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainRoute;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.TrainRouteService;
import ru.novand.orientexpress.utils.MessageSender;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class TrainRouteServiceImpl implements TrainRouteService {

    private static final Logger logger = LoggerFactory.getLogger(TrainRouteServiceImpl.class);

    @Autowired
    private TrainRouteDAO trainRouteDAO;

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    public TrainRoute addTrainRoute(String traincode,String depstationname, String arrstationname) {

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
            if (scheduleList.size()==0){
                scheduleList = scheduleDAO.getAllScheduleByTrainCode(trainRoute.getTrain().getTrainCode());
            }
            scheduleList.sort((a, b) -> Integer.compare(a.getInterval(), b.getInterval()));
            trainRoute.setScheduleList(scheduleList);
            scheduleList = new ArrayList<>();
        }

        return list;
    }

    public List<Train> getAllTrainsInTrainRoute() {

        List<Train> trains = trainDAO.findAll();

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

        MessageSender.sendMessage(1);

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

//        MessageSender.sendMessage(1);

        return trainRouteResult;
    }

    public void delete(String traincode) {
        TrainRoute trainRoute = trainRouteDAO.findByTrainCode(traincode);
        if (trainRoute!=null) {
            trainRouteDAO.delete(trainRoute);

        }
    }



    public List<Station> getAllStations() {

        try {
            List<Station> list = stationDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomSQLException("Unable to find stations!", ex);
        }
    }
}