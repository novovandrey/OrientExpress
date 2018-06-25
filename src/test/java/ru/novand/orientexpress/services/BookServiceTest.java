package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import ru.novand.orientexpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.services.impl.BookServiceImpl;
import ru.novand.orientexpress.services.interfaces.BookService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks BookService bookService = new BookServiceImpl(messageSource);

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
    public void testPassengerAlreadyRegisteredSuccess() {

        LocalDate mockdate = LocalDate.parse("2018-12-31");
        Instant depdate = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        List<Passenger> passengersmock = new ArrayList<Passenger>();
        Passenger passengermock = new Passenger("Andrey","Novov",birthdate);
        passengersmock.add(passengermock);

        // arrange
        List<Passenger> passengersexpected = new ArrayList<Passenger>();
        birthdateLD = LocalDate.parse("2010-05-15");
        birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        passengersexpected.add(new Passenger("Andrey","Novov",birthdate));

        // act
        when(ticketDAO.getAllPassengersByTrain("100",depdate)).thenReturn(passengersmock);

        boolean result = bookService.isPassengerAlreadyRegistered(100,depdate,passengermock);

        // assert
        assertTrue(result);
    }

    @Test
    public void testPassengerAlreadyRegisteredFail() {

        LocalDate mockdate = LocalDate.parse("2018-12-31");
        Instant depdate = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        List<Passenger> passengersmock = new ArrayList<Passenger>();
        Passenger passengermocktemp = new Passenger("Andrey","Novov",birthdate);
        Passenger passengermock = new Passenger("Kirill","Ivanov",birthdate);
        passengersmock.add(passengermocktemp);

        // arrange
        List<Passenger> passengersexpected = new ArrayList<Passenger>();
        birthdateLD = LocalDate.parse("2010-05-15");
        birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        passengersexpected.add(new Passenger("Andrey","Novov",birthdate));

        // act
        when(ticketDAO.getAllPassengersByTrain("100",depdate)).thenReturn(passengersmock);

        boolean result = bookService.isPassengerAlreadyRegistered(100,depdate,passengermock);

        // assert
        assertFalse(result);
    }
    @Test
    public void testCheckTrainExistByTrainCodeSuccess() {

        // arrange
        String trainCode = "100";
        Train trainmock = new Train("NoName",trainCode,2);

        // act
        when(trainDAO.findByCode("100")).thenReturn(trainmock);
        boolean result = bookService.checkTrainExistByTrainCode("100");

        // assert
        assertTrue(result);
    }
    @Test
    public void testCheckTrainExistByTrainCodeFail() {

        // arrange
        String trainCode = "100";
        Train trainmock = new Train("NoName",trainCode,2);

        // act
        when(trainDAO.findByCode("100")).thenReturn(trainmock);
        boolean result = bookService.checkTrainExistByTrainCode("101");

        // assert
        assertFalse(result);
    }

    @Test
    public void testcreatePassenegerSuccess() {

        // arrange
        String firstName="Ivan";
        String familyname="Novov";
        LocalDate mockdate = LocalDate.parse("2008-05-12");
        Instant birthday = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        String username="Ivan";

        User user = new User();
        //user.setEmail("ivan.ivanov@mail.ru");
        //user.setIdUser(1);
        user.setPassword("123456");
        user.setUsername("Ivan");

        Passenger passenegermock = new Passenger();
        passenegermock.setFirstname(firstName);
        passenegermock.setFamilyname(familyname);
        passenegermock.setBirthdate(birthday);
        // act
        when(userDAO.findByUsername(username)).thenReturn(user);
        when(passengerDAO.save(passenegermock)).thenReturn(passenegermock);

        Passenger result = bookService.createPasseneger( firstName,  familyname,  birthday,  username);

        // assert
        assertEquals(result,passenegermock);
    }

    @Test
    public void testcreatePassenegerFail() {
        // arrange
        String firstName="Ivan";
        String familyname="Novov";
        LocalDate mockdate = LocalDate.parse("2008-05-12");
        Instant birthday = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        String username="Ivan";

        User user = new User();
        //user.setEmail("ivan.ivanov@mail.ru");
        //user.setIdUser(1);
        user.setPassword("123456");
        user.setUsername("Ivan");

        Passenger passenegermock = new Passenger();
        passenegermock.setFirstname(firstName);
        passenegermock.setFamilyname(familyname);
        passenegermock.setBirthdate(birthday);

        Passenger passenegertemp = new Passenger();
        passenegertemp.setFirstname("Andrey");
        passenegertemp.setFamilyname(familyname);
        passenegertemp.setBirthdate(birthday);
        // act
        when(userDAO.findByUsername(username)).thenReturn(user);
        when(passengerDAO.save(passenegermock)).thenReturn(passenegermock);

        Passenger result = bookService.createPasseneger( firstName,  familyname,  birthday,  username);

        // assert
        assertNotEquals(result,passenegertemp);

    }

}