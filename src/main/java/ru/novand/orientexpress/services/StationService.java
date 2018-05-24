package ru.novand.orientexpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

