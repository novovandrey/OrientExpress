package ru.novand.orientexpress.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.novand.orientexpress.domain.DAO.Impl.TicketDAOImpl;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.impl.StationServiceImpl;
import ru.novand.orientexpress.services.impl.TicketServiceImpl;
import ru.novand.orientexpress.services.interfaces.StationService;
import ru.novand.orientexpress.services.interfaces.TicketService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketDAO ticketDAO;

    TicketService ticketService;

    @Before
    public void setUp() throws Exception {
        ticketService = new TicketServiceImpl(ticketDAO);
        initMocks(this);
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetTicketByPassengerIDSuccess() {

        Ticket ticket = new Ticket();
        ticket.setIdTicket(1);
        ticket.setTrainCode("100");
        LocalDate mockdate = LocalDate.parse("2018-12-31");
        Instant depdate = mockdate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        LocalDate birthdateLD = LocalDate.parse("2010-05-15");
        Instant birthdate = birthdateLD.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant();
        ticket.setDeparturedate(depdate);
        List<Ticket> ticketListExpected = new ArrayList<Ticket>();
        List<Ticket> ticketListResult = new ArrayList<Ticket>();
        ticketListExpected.add(ticket);
        Passenger passengermocktemp = new Passenger("Kirill","Ivanov",birthdate);
        passengermocktemp.setIdpassenger(1);
        // act
        when(ticketDAO.getTicketByPassengerID (1)).thenReturn(ticketListExpected);

        ticketListResult = ticketService.getTicketByPassengerID(1);
        ticketListResult = ticketListExpected;
        assertEquals(ticketListExpected,ticketListResult);
    }
}