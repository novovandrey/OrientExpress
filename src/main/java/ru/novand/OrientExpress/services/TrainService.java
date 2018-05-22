package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.entities.*;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TrainDAO trainDAO;

    public Train addTrain(String trainname,String traincode, int seatsnumber) {

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        schedules.add(new Schedule());
        Train train = new Train(trainname,traincode,seatsnumber);
        return trainDAO.save(train);
    }

    public List<Train> getAllTrains() {
        List<Train> list = trainDAO.findAll();
        return list;
    }
}