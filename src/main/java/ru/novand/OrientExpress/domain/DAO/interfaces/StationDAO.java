package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Station;

import java.util.List;

public interface StationDAO extends GenDAO<Station> {

    public List<Schedule> getTrains(String stationName);//trains arriving at the station arrival_station_id

    public Station getStation(String name);

}