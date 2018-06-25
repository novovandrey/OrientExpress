package ru.novand.orientexpress.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainScheduleDates;
import ru.novand.orientexpress.services.impl.TrainScheduleServiceImpl;
import ru.novand.orientexpress.services.interfaces.TrainScheduleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainScheduleServiceImplTest {

    @Mock
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Mock
    private TrainDAO trainDAO;

    @InjectMocks
    TrainScheduleService trainScheduleService = new TrainScheduleServiceImpl();

    @Test
    public void testAddTrainScheduleSuccess() {

        //arrange
        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);
        LocalDate mockdate = LocalDate.parse("2018-01-15");
        TrainScheduleDates trainScheduleDates = new TrainScheduleDates();
        trainScheduleDates.setIdtrainschedule(1);
        trainScheduleDates.setTrain(trainMock);
        trainScheduleDates.setDeparturedate(mockdate);

        TrainScheduleDates trainScheduleDatesResult = new TrainScheduleDates();
        TrainScheduleDates trainScheduleDatesExpected = new TrainScheduleDates();
        trainScheduleDatesExpected.setIdtrainschedule(1);
        trainScheduleDatesExpected.setTrain(trainMock);
        trainScheduleDatesExpected.setDeparturedate(mockdate);

        //act
        when(trainDAO.findByCode(anyString())).thenReturn(trainMock);
        when(trainScheduleDatesDAO.save(anyObject())).thenReturn(trainScheduleDates);
        trainScheduleDatesResult = trainScheduleService.addTrainSchedule("100","2018-01-15");

        //assert
        assertEquals(trainScheduleDatesExpected,trainScheduleDatesResult);

    }

    @Test
    public void testGetTrainScheduleListSuccess() {

        //arrange
        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);
        LocalDate mockdate = LocalDate.parse("2018-01-15");
        TrainScheduleDates trainScheduleDates = new TrainScheduleDates();
        trainScheduleDates.setIdtrainschedule(1);
        trainScheduleDates.setTrain(trainMock);
        trainScheduleDates.setDeparturedate(mockdate);
        List<TrainScheduleDates> trainScheduleDatesList = new ArrayList<>();
        trainScheduleDatesList.add(trainScheduleDates);

        List<TrainScheduleDates> trainScheduleDatesResultList = new ArrayList<>();
        List<TrainScheduleDates> trainScheduleDatesExpectedList = new ArrayList<>();
        trainScheduleDatesExpectedList.add(trainScheduleDates);

        //act
        when(trainScheduleDatesDAO.findAll()).thenReturn(trainScheduleDatesList);
        trainScheduleDatesResultList = trainScheduleService.getTrainScheduleList();
        //assert
        assertEquals(trainScheduleDatesExpectedList.get(0),trainScheduleDatesResultList.get(0));
    }
}