package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainRoute;
import ru.novand.orientexpress.services.impl.TrainServiceImpl;
import ru.novand.orientexpress.services.interfaces.TrainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(MockitoJUnitRunner.class)
public class TrainServiceImplTest {

    TrainService trainService;

    @Mock
    private TrainDAO trainDAO;

    @Before
    public void setUp() throws Exception {
        trainService = new TrainServiceImpl(trainDAO);
        initMocks(this);
    }
    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void addTrain() {

        //arrange
        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);

        Train trainResalt = new Train();
        Train trainExpected = new Train();
        trainExpected.setIdTrain(12);
        trainExpected.setTrainCode("100");
        trainExpected.setTrainSeats(10);
        trainResalt = trainExpected;
        Train trainResult = new Train();
        // act
        when(trainDAO.save(trainMock)).thenReturn(trainMock);

        trainResult = trainService.addTrain("Test","100", 1);
        assertEquals(trainExpected,trainResalt);
    }

    @Test
    public void getAllTrains() {

        //arrange
        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);
        List<Train> trainListMock = new ArrayList<>();
        trainListMock.add(trainMock);
        List<Train> trainListExpected = new ArrayList<>();
        trainListExpected = trainListMock;
        List<Train> trainListResult = new ArrayList<>();
        trainListResult = trainListMock;
        List<Train> trainListReslt = new ArrayList<>();
        // act
        when(trainDAO.findAll()).thenReturn(trainListMock);

        //assert
        trainListReslt = trainService.getAllTrains();
        assertEquals(trainListExpected,trainListResult);
    }

}