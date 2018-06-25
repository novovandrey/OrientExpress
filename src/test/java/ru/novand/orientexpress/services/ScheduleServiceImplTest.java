package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.*;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.*;
import ru.novand.orientexpress.services.impl.ScheduleServiceImpl;
import ru.novand.orientexpress.services.interfaces.ScheduleService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceImplTest {

    @Mock
    private TrainTariffDAO trainTariffDAO;

    @Mock
    private ScheduleDAO scheduleDAO;

    @Mock
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Mock
    private TrainRouteDAO trainRouteDAO;

    @Mock
    private TrainRouteDAO trainDAO;

    @Mock
    private StationDAO stationDAO;

    @InjectMocks
    ScheduleService scheduleService = new ScheduleServiceImpl();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAllStationsSuccess() {

        //arrange
        Station station = new Station();
        station.setIdStation(1);
        station.setStationname("NewStation");

        List<Station> trainListResult = new ArrayList<Station>();
        List<Station> trainListExpected = new ArrayList<Station>();
        trainListExpected.add(station);

        //act
        when(stationDAO.findAll()).thenReturn(trainListExpected);
        trainListResult = scheduleService.getAllStations();

        //assert
        assertEquals(trainListExpected,trainListResult);

    }

    @Test
    public void testGetAllStationsFail() {

        //arrange
        Station station = new Station();
        station.setIdStation(1);
        station.setStationname("NewStation");

        //arrange
        Station station2 = new Station();
        station2.setIdStation(1);
        station2.setStationname("NewStation2");

        List<Station> trainListResult = new ArrayList<Station>();
        List<Station> trainListExpected = new ArrayList<Station>();
        trainListExpected.add(station);
        trainListResult.add(station2);

        //act
        when(stationDAO.findAll()).thenReturn(trainListExpected);
        trainListResult = scheduleService.getAllStations();

        //assert
        //assertNotEquals(trainListExpected,trainListResult);

    }

    @Test
    public void testGetScheduleSuccess() {

        //arrange
        String fromSt = "NewStation";
        String toSt = "NewStation2";
        String departuredate = "2018-01-15";
        String arrivaldate = "2018-01-18";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date depdate = null;
        Date arrdate=null;
        try {
            depdate = sdf.parse(departuredate);
            arrdate = sdf.parse(arrivaldate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO("100",depdate,fromSt,arrdate,toSt);

        //arrange
        List<ScheduleDTO> scheduleDTOResult = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOExpected = new ArrayList<>();

        scheduleDTOExpected.add(scheduleDTO);

        //act
        when(scheduleDAO.getSchedule(anyString(),anyString(),anyString())).thenReturn(scheduleDTOExpected);
        scheduleDTOResult = scheduleService.getSchedule(fromSt, toSt, departuredate);

        //assert
        assertEquals(scheduleDTOResult,scheduleDTOResult);

    }

    @Test
    public void testGetTrainTariffSuccess() {

        //arrange
        String traincode = "100";
        String departuredate = "2018-01-15";
        TrainTariff trainTariff = new TrainTariff();

        List<TrainTariff> trainTariffListResult = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListResult = new ArrayList<>();
        List<TrainTariff> trainTariffListExpected = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListExpected = new ArrayList<>();

        //act
        when(trainTariffDAO.getAllTrainTariffByTrainCode(anyString())).thenReturn(trainTariffListExpected);
        scheduleDTOListResult = scheduleService.getTrainTariff(traincode,departuredate);

        //assert
        assertEquals(scheduleDTOListResult,scheduleDTOListExpected);
    }

    @Test
    public void testGetTariffOrderedSuccess() {

        //arrange
        List<ScheduleDTO> scheduleDTOListResult = new ArrayList<>();
        String traincode = "";
        String fromSt = "Moscow";
        String toSt = "SaintPetersburg";
        String station1 =  "Tver";
        String station2 =  "SaintPetersburg";
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ScheduleDTO scheduleDTO = new ScheduleDTO(traincode,station1,station2,bigDecimal);
        scheduleDTOListResult.add(scheduleDTO);
        station1 = "Moscow";
        station2 = "Tver";
        bigDecimal = BigDecimal.valueOf(5);
        scheduleDTO = new ScheduleDTO(traincode,station1,station2,bigDecimal);
        scheduleDTOListResult.add(scheduleDTO);

        List<ScheduleDTO> scheduleDTOListExpected = new ArrayList<>();
        scheduleDTOListExpected.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));
        station1 =  "Tver";
        station2 =  "SaintPetersburg";
        bigDecimal = BigDecimal.valueOf(10);
        scheduleDTOListExpected.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));
        //act
        scheduleDTOListResult = scheduleService.getTariffOrdered(scheduleDTOListResult,fromSt,toSt);

        //assert
        assertEquals(scheduleDTOListResult.size(),scheduleDTOListExpected.size());

        assertEquals(scheduleDTOListResult.get(0),scheduleDTOListExpected.get(0));
        assertEquals(scheduleDTOListResult.get(1),scheduleDTOListExpected.get(1));
    }

    @Test
    public void testGetCityListSuccess() {

        //arrange
        String traincode = "";
        String fromSt = "Moscow";
        String toSt = "SaintPetersburg";
        String station1 = "Moscow";
        String station2 = "Tver";
        BigDecimal bigDecimal = BigDecimal.valueOf(5);

        List<ScheduleDTO> scheduleDTOListOrdered = new ArrayList<>();
        scheduleDTOListOrdered.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));
        station1 =  "Tver";
        station2 =  "SaintPetersburg";
        bigDecimal = BigDecimal.valueOf(10);
        scheduleDTOListOrdered.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));

        List<String> citylistResult = new ArrayList<>();
        List<String> citylistExpected = new ArrayList<>();
        citylistExpected.add("Tver");
        citylistExpected.add("SaintPetersburg");
        //act
        citylistResult = scheduleService.getCityList(scheduleDTOListOrdered);
        //assert
        assertEquals(citylistResult.size(),citylistExpected.size());
        assertEquals(citylistResult.get(0),citylistExpected.get(0));
        assertEquals(citylistResult.get(1),citylistExpected.get(1));

    }

    @Test
    public void testGetTariffSuccess() {

        //arrange
        String traincode = "";
        String fromSt = "Moscow";
        String toSt = "SaintPetersburg";
        String station1 = "Moscow";
        String station2 = "Tver";
        BigDecimal bigDecimal = BigDecimal.valueOf(5);

        List<ScheduleDTO> scheduleDTOListOrdered = new ArrayList<>();
        scheduleDTOListOrdered.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));
        station1 =  "Tver";
        station2 =  "SaintPetersburg";
        bigDecimal = BigDecimal.valueOf(10);
        scheduleDTOListOrdered.add(new ScheduleDTO(traincode,station1,station2,bigDecimal));

        BigDecimal tariffvalueResult = BigDecimal.valueOf(0);
        BigDecimal tariffvalueExpected = BigDecimal.valueOf(15);
        //act
        tariffvalueResult = scheduleService.getTariff(scheduleDTOListOrdered);
        //assert
        assertEquals(tariffvalueExpected,tariffvalueResult);

    }
    @Ignore("will be Implement later")
    @Test
    public void convertToDateViaInstant() {
    }

    @Test
    public void testGetScheduleByStationSuccess() {

        //arrange
        String departuredate = "2018-01-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date depdate = null;
        try {
            depdate = sdf.parse(departuredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date depdateFormat = new Date();
        LocalDate depdateLD = Instant.ofEpochMilli(depdate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        String traincode = "100";
        TrainTariff trainTariff = new TrainTariff();

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

        List<Schedule> scheduleListResult = new ArrayList<>();
        scheduleListResult.add(new Schedule("",trainMock,arrivalstation,departurestation,1));

        List<TrainScheduleDates> trainscheduledatesList = new ArrayList<>();
        TrainScheduleDates trainScheduleDates = new TrainScheduleDates();
        trainScheduleDates.setDeparturedate(depdateLD);
        trainScheduleDates.setTrain(trainMock);
        trainScheduleDates.setIdtrainschedule(1);
        trainscheduledatesList.add(trainScheduleDates);

        List<ScheduleDTO> scheduleDTOListResult = new ArrayList<>();
        List<ScheduleDTO> scheduleDTOListExpected = new ArrayList<>();
        String departuredateFormat = "15.01.2018";
        ScheduleDTO scheduleDTOExpected =new ScheduleDTO("100", depdate,"NewStation","NewStation2");
        scheduleDTOListExpected.add(scheduleDTOExpected);

        TrainRoute trainRoute = new TrainRoute();
        trainRoute.setArrivalstation(arrivalstation);
        trainRoute.setDeparturestation(departurestation);
        trainRoute.setTrain(trainMock);
        trainRoute.setIdTrainRoute(1);
        //act
        when(scheduleDAO.getTrains(anyString())).thenReturn(scheduleListResult);
        when(trainScheduleDatesDAO.findAll()).thenReturn(trainscheduledatesList);
        when(trainRouteDAO.findByTrainCode(anyString())).thenReturn(trainRoute);
        scheduleDTOListResult = scheduleService.getScheduleByStation(traincode,departuredateFormat);

        //assert
        //assertEquals(scheduleDTOListResult,scheduleDTOListExpected);

    }

    @Test
    public void delete() {

        //arrange
        String departuredate = "2018-01-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date depdate = null;
        try {
            depdate = sdf.parse(departuredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date depdateFormat = new Date();
        LocalDate depdateLD = Instant.ofEpochMilli(depdate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        String traincode = "100";
        TrainTariff trainTariff = new TrainTariff();

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

        //act
        when(scheduleDAO.findById(anyInt())).thenReturn(scheduleResult);
        scheduleService.delete(anyInt());


    }

    @Test
    public void get() {

        //arrange
        String departuredate = "2018-01-15";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date depdate = null;
        try {
            depdate = sdf.parse(departuredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date depdateFormat = new Date();
        LocalDate depdateLD = Instant.ofEpochMilli(depdate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        String traincode = "100";
        TrainTariff trainTariff = new TrainTariff();

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
        Schedule scheduleExpected;
        //act
        when(scheduleDAO.findById(anyInt())).thenReturn(scheduleResult);
        scheduleExpected = scheduleService.get(anyInt());
        assertEquals(scheduleExpected,scheduleResult);
    }
}