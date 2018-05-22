package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.OrientExpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.dto.ScheduleDTO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private TrainDAO trainDAO;

    public Ticket saveTicket(String trainCode, String fromSt, String toSt, Date depdate, Passenger passenger) throws CustomSQLException {

        Ticket ticket = new Ticket(passenger, trainCode, depdate);
        ticketDAO.save(ticket);
        return ticket;
    }

    public boolean checkVacantPlaces(int trainCode, Date depdateFormat) {
        int tickets = ticketDAO.countTicketsOnTrain(String.valueOf(trainCode),depdateFormat);
        int ticketsAvalible = trainDAO.findByCode(String.valueOf(trainCode)).getTrainSeats();
        return (ticketsAvalible <= tickets);
    }

    public boolean isPassengerAlreadyRegistered(int trainCode, Date depdateFormat, Passenger passenger) {
        List<Passenger> passengerList = ticketDAO.GetAllPassengersByTrain(String.valueOf(trainCode),depdateFormat);
        return passengerList.contains(passenger);
    }


    public List<Passenger> getAllPassengers(String trainCode, Date depdate) throws CustomSQLException {

        return ticketDAO.GetAllPassengersByTrain (trainCode,depdate);
    }

    public List<Ticket> getTicketByPassengerID(int passengerId) throws CustomSQLException {

        return ticketDAO.getTicketByPassengerID (passengerId);
    }

    public String convertDateTimeToDate_ddMMyyyy(String departuredate)  {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime formatDateTime = LocalDateTime.parse(departuredate, formatter);
        return formatDateTime.format(ddMMyyyyformatter);
    }

    public List<String> convertISODateTimeToDateAndTime(String departuredate)  {

        List<String> dateTimeStr = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDate depdateFormatDate = LocalDate.parse(departuredate,formatter );

        LocalDateTime depdateFormatTime = LocalDateTime.parse(departuredate,formatter );

        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateTimeStr.add(depdateFormatDate.format(formatter));

        formatter = DateTimeFormatter.ofPattern("HH:mm");
        dateTimeStr.add(depdateFormatTime.format(formatter));

        return dateTimeStr;
    }

}
