package ru.novand.OrientExpress.domain.DAO.interfaces;

import ru.novand.OrientExpress.domain.entities.Schedule;

import java.util.List;

public interface ScheduleDAO extends GenDAO<Schedule> {
    public List<Schedule> getTrains(String stationName);//trains arriving at the station arrival_station_id
}
