package ru.novand.OrientExpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Ticket save(Ticket entity) {
        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save station: " + ex);
        }
        return entity;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> result = null;
        TypedQuery<Ticket> query = manager.createNamedQuery("Ticket.findAll", Ticket.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public Ticket delete(Ticket entity) {
        try {
            manager.remove(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove station: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket update(Ticket entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update station: " + ex);
        }
        return entity;
    }

    @Override
    public Ticket findById(int id) {
        Ticket result = manager.find(Ticket.class, id);
        return result;
    }

    @Override
    public int countTicketsOnTrain(String trainCode, Date departuredate) {
        String query = "Select count(t) from Ticket t where t.trainCode = :trainCode and t.departuredate = :departuredate";
        Query q = manager.createQuery(query);

        q.setParameter("trainCode", trainCode);
        q.setParameter("departuredate", departuredate);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<Passenger> getAllPassengersByTrain(String trainCode, Date departuredate) {

        List<Passenger> passengers = new ArrayList<>();

        TypedQuery<Ticket> query = manager.createNamedQuery("Ticket.findTicketsByTrainCode", Ticket.class);
        query.setParameter("trainCode", trainCode);
        query.setParameter("departuredate", departuredate, TemporalType.DATE);
        List<Ticket> tickets = query.getResultList();

        for (Ticket ticket:tickets) {
            passengers.add(ticket.getPassenger());
        }
        return passengers;
    }

    @Override
    public List<Ticket> getTicketByPassengerID(int passengerId) {

        String query = "from Ticket where passenger.idpassenger = :passengerId";
        Query q = manager.createQuery(query);
        q.setParameter("passengerId", passengerId);

        return q.getResultList();
    }

}
