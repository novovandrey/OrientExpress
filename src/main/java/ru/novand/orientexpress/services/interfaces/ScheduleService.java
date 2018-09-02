package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.TrainRoute;
import ru.novand.orientexpress.domain.entities.TrainScheduleDates;
import ru.novand.orientexpress.exception.CustomSQLException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

    public List<Station> getAllStations();

    public List<ScheduleDTO> getSchedule(String fromSt, String toSt, String departuredate);

    public List<ScheduleDTO> getTrainTariff(String traincode, String departuredate);
    public List<ScheduleDTO> getTariffOrdered( List<ScheduleDTO> tariff,String fromSt, String toSt);
    public List<String> getCityList( List<ScheduleDTO> tariffOrdered);
    public BigDecimal getTariff(List<ScheduleDTO> tariffOrdered);

    Date convertToDateViaInstant(LocalDateTime dateToConvert);

    public List<ScheduleDTO> getScheduleByStation(String stationname, String departuredate);

    public void delete(int id);
    public Schedule save(String fromst,String tost, String interval, String traincode);
    public void update(String fromst,String tost, String interval, int id);
    public Schedule get(int id);
}
