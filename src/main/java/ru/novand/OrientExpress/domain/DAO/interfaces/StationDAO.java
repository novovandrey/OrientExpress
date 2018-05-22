package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Station;

import java.util.List;

public interface StationDAO extends GenDAO<Station> {

    public Station getStation(String name);

}