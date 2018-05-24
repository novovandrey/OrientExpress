package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.Schedule;

import java.util.List;

public interface ScheduleDAO extends GenDAO<Schedule> {
    public List<Schedule> getTrains(String stationName);//trains arriving at the station arrival_station_id
}
