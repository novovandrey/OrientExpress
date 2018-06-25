package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.ScheduleDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainRoute;
import ru.novand.orientexpress.services.impl.TrainRouteServiceImpl;
import ru.novand.orientexpress.services.interfaces.TrainRouteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class TrainRouteServiceImplTest {

    @Mock
    private TrainRouteDAO trainRouteDAO;

    @Mock
    private StationDAO stationDAO;

    @Mock
    private TrainDAO trainDAO;

    @Mock
    private ScheduleDAO scheduleDAO;

    @InjectMocks
    TrainRouteService trainRouteService = new TrainRouteServiceImpl();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddTrainRouteSuccess() {

        Station arrivalstation = new Station();
        arrivalstation.setIdStation(1);
        arrivalstation.setStationname("NewStation");

        Station departurestation = new Station();
        departurestation.setIdStation(1);
        departurestation.setStationname("NewStation2");

        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);

        TrainRoute trainRouteResult = new TrainRoute();
        TrainRoute trainRouteMock = new TrainRoute();
        trainRouteMock.setIdTrainRoute(1);
        trainRouteMock.setTrain(trainMock);
        trainRouteMock.setDeparturestation(departurestation);
        trainRouteMock.setArrivalstation(arrivalstation);

        TrainRoute trainRouteExpect = new TrainRoute();
        trainRouteExpect.setIdTrainRoute(1);
        trainRouteExpect.setTrain(trainMock);
        trainRouteExpect.setDeparturestation(departurestation);
        trainRouteExpect.setArrivalstation(arrivalstation);

        // act
        when(trainRouteDAO.save(trainRouteResult)).thenReturn(trainRouteMock);
        trainRouteResult = trainRouteService.addTrainRoute("100","NewStation", "NewStation2");
        // assert
        assertEquals(trainRouteResult,trainRouteExpect);

    }


    @Test
    public void testGetAllTrainRouteSuccess() {

        Station arrivalstation = new Station();
        arrivalstation.setIdStation(1);
        arrivalstation.setStationname("NewStation");

        Station departurestation = new Station();
        departurestation.setIdStation(1);
        departurestation.setStationname("NewStation2");

        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);

        Schedule scheduleResult = new Schedule("",trainMock,arrivalstation,departurestation,1);
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(scheduleResult);

        TrainRoute trainRouteResult = new TrainRoute();
        trainRouteResult.setIdTrainRoute(1);
        trainRouteResult.setTrain(trainMock);
        trainRouteResult.setDeparturestation(departurestation);
        trainRouteResult.setArrivalstation(arrivalstation);
        trainRouteResult.setScheduleList(scheduleList);

        List<TrainRoute> trainRouteResultList = new ArrayList<>();
        List<TrainRoute> trainRouteExpectList = new ArrayList<>();

        trainRouteExpectList.add(trainRouteResult);

        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRouteResult);

        TrainRoute trainRouteExpect = new TrainRoute();
        trainRouteExpect.setIdTrainRoute(1);
        trainRouteExpect.setTrain(trainMock);
        trainRouteExpect.setDeparturestation(departurestation);
        trainRouteExpect.setArrivalstation(arrivalstation);

        // act
        when(trainRouteDAO.findAll()).thenReturn(trainRouteList);
        when(scheduleDAO.getAllScheduleByTrainCode(anyString())).thenReturn(scheduleList);

        trainRouteResultList = trainRouteService.getAllTrainRoute();
        // assert
        assertEquals(trainRouteExpectList.get(0),trainRouteResultList.get(0));

    }

    @Test
    public void testGetAllTrainsInTrainRouteSuccess() {

        Station arrivalstation = new Station();
        arrivalstation.setIdStation(1);
        arrivalstation.setStationname("NewStation");

        Station departurestation = new Station();
        departurestation.setIdStation(1);
        departurestation.setStationname("NewStation2");

        Train trainMock = new Train();
        trainMock.setIdTrain(12);
        trainMock.setTrainCode("100");
        trainMock.setTrainSeats(10);

        Train trainMock2 = new Train();
        trainMock2.setIdTrain(1);
        trainMock2.setTrainCode("101");
        trainMock2.setTrainSeats(12);

        List<Train> trainList = new ArrayList<>();
        trainList.add(trainMock);
        trainList.add(trainMock2);

        List<Train> trainResultList = new ArrayList<>();
        List<Train> trainExprectedList = new ArrayList<>();
        trainExprectedList.add(trainMock2);

        Schedule scheduleResult = new Schedule("",trainMock,arrivalstation,departurestation,1);
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(scheduleResult);

        TrainRoute trainRouteResult = new TrainRoute();
        trainRouteResult.setIdTrainRoute(1);
        trainRouteResult.setTrain(trainMock);
        trainRouteResult.setDeparturestation(departurestation);
        trainRouteResult.setArrivalstation(arrivalstation);
        trainRouteResult.setScheduleList(scheduleList);

        List<TrainRoute> trainRouteExpectList = new ArrayList<>();

        trainRouteExpectList.add(trainRouteResult);

        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRouteResult);

        TrainRoute trainRouteExpect = new TrainRoute();
        trainRouteExpect.setIdTrainRoute(1);
        trainRouteExpect.setTrain(trainMock);
        trainRouteExpect.setDeparturestation(departurestation);
        trainRouteExpect.setArrivalstation(arrivalstation);

        // act
        when(trainRouteDAO.findAll()).thenReturn(trainRouteList);
        when(trainDAO.findAll()).thenReturn(trainList);

        trainResultList = trainRouteService.getAllTrainsInTrainRoute();
        // assert
        assertEquals(trainExprectedList.get(0),trainResultList.get(0));

    }

    @Ignore("update")
    @Test
    public void update() {

    }

    @Ignore("save")
    @Test
    public void save() {
    }

    @Ignore("delete")
    @Test
    public void delete() {
    }

    @Ignore("getAllStations")
    @Test
    public void getAllStations() {
    }
}