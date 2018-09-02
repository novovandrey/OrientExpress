package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TicketDAO extends GenDAO<Ticket> {

    public int countTicketsOnTrain(String trainCode, Instant departuredate);
    public List<Passenger> getAllPassengersByTrain(String trainCode, Instant departuredate);
    //@Query("select u from user u where u.id = :passengerId")
    public List<Ticket> getTicketByPassengerID(int passengerId);
}

