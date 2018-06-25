package ru.novand.orientexpress.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.PassengerService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private TrainDAO trainDAO;

    private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);

    public Passenger getPassengerByUserName(String username) throws CustomSQLException {

        Passenger result = passengerDAO.findByUserName(username);
        return result;
    }

    public List<Passenger> getAllPassengers(String trainCode, Instant depdate) throws CustomSQLException {

        return ticketDAO.getAllPassengersByTrain(trainCode,depdate);
    }

    public List<Train> getAllTrains() throws CustomSQLException {

        List<Train> list = trainDAO.findAll();
        return list;
    }

}
