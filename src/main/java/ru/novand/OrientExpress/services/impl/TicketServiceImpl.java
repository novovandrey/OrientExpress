package ru.novand.orientexpress.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    @Autowired
    public TicketServiceImpl(final TicketDAO ticketDAO) {

        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> getTicketByPassengerID(int passengerId) throws CustomSQLException {

        return ticketDAO.getTicketByPassengerID (passengerId);
    }


}
