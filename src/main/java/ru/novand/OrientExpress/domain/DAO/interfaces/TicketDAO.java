package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketDAO extends GenDAO<Ticket> {

    public int countTicketsOnTrain(String trainCode, LocalDate departuredate);
    public List<Passenger> GetAllPassengersByTrain(String trainCode, LocalDate departuredate);
}

