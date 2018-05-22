package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.OrientExpress.domain.DAO.Impl.TrainDAOImpl;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.OrientExpress.domain.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainScheduleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Autowired
    private TrainDAO trainDAO;


    public TrainScheduleDates addTrainSchedule(String traincode, String departuredate) {

        Train train =  trainDAO.findByCode(traincode);
        TrainScheduleDates trainScheduleDates = new TrainScheduleDates();
        trainScheduleDates.setTrain(train);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate departuredateFormat = LocalDate.parse(departuredate, formatter);

        trainScheduleDates.setDeparturedate(departuredateFormat);
        return trainScheduleDatesDAO.save(trainScheduleDates);
    }

    public List<TrainScheduleDates> getTrainScheduleList() {
        List<TrainScheduleDates> list = trainScheduleDatesDAO.findAll();
        return list;
    }


}