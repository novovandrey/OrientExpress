package ru.novand.orientexpress.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.services.interfaces.StationService;

@Service
public class StationServiceImpl implements StationService {

    private final StationDAO stationDAO;

    @Autowired
    public StationServiceImpl(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public Station addStation(String stationname) throws CustomSQLException {

        Station station = new Station(stationname);
        return stationDAO.save(station);
    }

}

