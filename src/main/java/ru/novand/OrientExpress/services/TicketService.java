package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.dto.ScheduleDto;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.domain.entities.TrainRoute;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Transactional //need to update\delete queries.
@Service
public class TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private TrainDAO trainDAO;

    public Ticket saveTicket(String trainCode, String fromSt, String toSt, String depdate, Passenger passenger) throws CustomSQLException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date depdateFormat = null;
        try {
            depdateFormat = formatter.parse(depdate);
        } catch (ParseException ex) {
        }

        Ticket ticket = new Ticket(passenger, trainCode, depdateFormat);
        ticketDAO.save(ticket);
        return ticket;
    }

    public boolean checkVacantPlaces(int trainCode, LocalDate depdateFormat) {
        int tickets = ticketDAO.countTicketsOnTrain(String.valueOf(trainCode),depdateFormat);
        int ticketsAvalible = trainDAO.findByCode(String.valueOf(trainCode)).getTrainSeats();
        return (ticketsAvalible <= tickets);
    }

    public boolean isPassengerAlreadyRegistered(int trainCode, LocalDate depdateFormat, Passenger passenger) {
        List<Passenger> passengerList = ticketDAO.GetAllPassengersByTrain(String.valueOf(trainCode),depdateFormat);
        return passengerList.contains(passenger);
    }


}
