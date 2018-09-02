package ru.novand.orientexpress.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.orientexpress.domain.entities.*;
import ru.novand.orientexpress.services.interfaces.TrainScheduleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TrainScheduleServiceImpl implements TrainScheduleService {

    @Autowired
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Autowired
    private TrainDAO trainDAO;


    public TrainScheduleDates addTrainSchedule(String traincode, String departuredate) {

        Train train =  trainDAO.findByCode(traincode);
        TrainScheduleDates trainScheduleDates = new TrainScheduleDates();
        trainScheduleDates.setTrain(train);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime departuredateFormatLD = LocalDateTime.parse(departuredate, formatter);
        Instant departuredateFormatInstant = departuredateFormatLD.toInstant(ZoneOffset.UTC);
        trainScheduleDates.setDeparturedate(departuredateFormatInstant);
        return trainScheduleDatesDAO.save(trainScheduleDates);
    }

    public List<TrainScheduleDates> getTrainScheduleList() {
        List<TrainScheduleDates> list = trainScheduleDatesDAO.findAll();
        return list;
    }


}