package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.impl.PassengerServiceImpl;
import ru.novand.orientexpress.services.interfaces.PassengerService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceImplTest {

    @InjectMocks
    PassengerService passengerService = new PassengerServiceImpl();

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private TrainDAO trainDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private PassengerDAO passengerDAO;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPassengerByUserNameSuccess() {

        //arrange
        String username="Ivan";
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        Passenger passengermock = new Passenger("Ivan","Ivanov",birthdate);
        // act
        when(passengerDAO.findByUserName(username)).thenReturn(passengermock);
        Passenger result = passengerService.getPassengerByUserName(username);
        // assert
        assertEquals(result,passengermock);
    }

    @Test
    public void testGetPassengerByUserNameFail() {

        //arrange
        String username="Ivan";
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        Passenger passengermock = new Passenger("Ivan","Ivanov",birthdate);
        Passenger passengermockExpected = new Passenger("Ivan","Ivanov",birthdate);
        // act
        when(passengerDAO.findByUserName(username)).thenReturn(passengermock);
        Passenger result = passengerService.getPassengerByUserName(username);
        // assert
        assertEquals(result,passengermockExpected);
    }

    @Test
    public void testGetAllPassengersSuccess() {

        LocalDate mockdate = LocalDate.parse("2018-12-31");
        Instant depdate = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        List<Passenger> passengersmock = new ArrayList<Passenger>();
        Passenger passengermocktemp = new Passenger("Andrey","Novov",birthdate);
        //Passenger passengermock = new Passenger("Kirill","Ivanov",birthdate);
        passengersmock.add(passengermocktemp);

        // arrange
        String trainCode = "100";
        List<Passenger> passengersexpected = new ArrayList<Passenger>();
        birthdateLD = LocalDate.parse("2010-05-15");
        birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        passengersexpected.add(new Passenger("Andrey","Novov",birthdate));
        List<Passenger> passengersResult = new ArrayList<Passenger>();

        // act
        when(ticketDAO.getAllPassengersByTrain("100",depdate)).thenReturn(passengersmock);
        passengersResult = passengerService.getAllPassengers( trainCode,  depdate);

        //assert
        assertEquals(passengersResult,passengersexpected);
    }

    @Test
    public void testGetAllPassengersFail() {

        LocalDate mockdate = LocalDate.parse("2018-12-31");
        Instant depdate = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        List<Passenger> passengersmock = new ArrayList<Passenger>();
        Passenger passengermocktemp = new Passenger("Kirill","Ivanov",birthdate);
        passengersmock.add(passengermocktemp);

        // arrange
        String trainCode = "100";
        List<Passenger> passengersexpected = new ArrayList<Passenger>();
        birthdateLD = LocalDate.parse("2010-05-15");
        birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        passengersexpected.add(new Passenger("Ivan","Ivanov",birthdate));
        List<Passenger> passengersResult = new ArrayList<Passenger>();

        // act
        when(ticketDAO.getAllPassengersByTrain("100",depdate)).thenReturn(passengersmock);
        passengersResult = passengerService.getAllPassengers( trainCode,  depdate);

        //assert
        assertNotEquals(passengersResult,passengersexpected);
    }

    @Test
    public void testGetAllTrainsSuccess() {

        //arrange
        Train train1 = new Train();
        train1.setIdTrain(12);
        train1.setTrainCode("100");
        train1.setTrainSeats(10);

        List<Train> trainListResult = new ArrayList<Train>();
        List<Train> trainListExpected = new ArrayList<Train>();
        trainListExpected.add(train1);

        //act
        when(trainDAO.findAll()).thenReturn(trainListExpected);
        trainListResult = passengerService.getAllTrains();

        //assert
        assertEquals(trainListExpected,trainListResult);
    }

    @Test
    public void testGetAllTrainsFail() {

        Train train1 = new Train();
        train1.setIdTrain(12);
        train1.setTrainCode("100");
        train1.setTrainSeats(10);

        Train train2 = new Train();
        train2.setIdTrain(2);
        train2.setTrainCode("101");
        train2.setTrainSeats(10);

        List<Train> trainListResult = new ArrayList<Train>();
        List<Train> trainListMock = new ArrayList<Train>();
        trainListMock.add(train2);
        List<Train> trainListExpected = new ArrayList<Train>();
        trainListExpected.add(train1);

        when(trainDAO.findAll()).thenReturn(trainListMock);
        trainListResult = passengerService.getAllTrains();

        assertNotEquals(trainListExpected,trainListResult);
    }
}