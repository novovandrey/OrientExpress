package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Schedule;

import java.util.List;

public interface ScheduleDAO extends GenDAO<Schedule> {
    public List<Schedule> getTrains(String stationName);//trains arriving at the station arrival_station_id
    public List<Schedule> getAllScheduleByTrainCode(String traincode);
    public List<ScheduleDTO> getSchedule(String fromSt, String toSt, String departuredate);
}
