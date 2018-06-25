package ru.novand.orientexpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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

    public boolean checkTrainExistByTrainCode(String trainCode) {
        Train result = trainDAO.findByCode(trainCode);
        if (result!=null) return true;
        return false;
    }
}