package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.exception.CustomSQLException;

import java.util.List;

public interface TicketService {
    public List<Ticket> getTicketByPassengerID(int passengerId);
}
