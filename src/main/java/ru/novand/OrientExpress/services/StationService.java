package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TicketDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainDAO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class StationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StationDAO stationDAO;

    public Station addStation(String stationname) throws CustomSQLException {

        Station station = new Station(stationname);
        return stationDAO.save(station);
    }

}

