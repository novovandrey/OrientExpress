package ru.novand.orientexpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.ScheduleDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.StationDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainRouteDAO;
import ru.novand.orientexpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.*;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional //need to update\delete queries.

public class ScheduleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private TrainRouteDAO trainRouteDAO;

    public List<Station> GetAllStations() {
        //TODO bcrypt for md5hash
        //TODO 3 attempt for password

        try {
            List<Station> list = stationDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomSQLException("Unable to find stations!", ex);
        }
    }

    public boolean checkSchedule(String fromSt, String toSt, String departuredate){
        List<ScheduleDTO> routeList = getSchedule(fromSt,toSt,departuredate);
        if(routeList.isEmpty()) return false;
        return true;
    }

    public List<ScheduleDTO> getSchedule(String fromSt, String toSt, String departuredate) {
        System.out.println("ScheduleService getSchedule is called");

        //TODO timezones store UTC
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusDays(1);
        Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String query ="SELECT tr.TRAINCODE, date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE ), st1.STATIONNAME as st1,date_add(d1.DEPARTUREDATE,INTERVAL s2.INTERVAL_M MINUTE ), st2.STATIONNAME  as st2 FROM Schedule s1 join Schedule s2 on s1.TRAIN_ID = s2.TRAIN_ID join TrainScheduleDates d1 on s1.TRAIN_ID = d1.TRAIN_ID join Station st1 on st1.STATION_ID = s1.DEPARTURE_STATION_ID join Station st2 on st2.STATION_ID = s2.DEPARTURE_STATION_ID join Train tr on tr.TRAIN_ID = s1.TRAIN_ID where st1.STATIONNAME = '#fromSt' and st2.STATIONNAME = '#toSt' and date(date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE )) = date(STR_TO_DATE('#date','%d.%m.%Y'))";
        query = query.replaceAll("#date",departuredate);
        query = query.replaceAll("#toSt",toSt);
        query = query.replaceAll("#fromSt",fromSt);
        List<Object[]> schedulelist = entityManager.createNativeQuery(query)
                .getResultList();

        ArrayList scheduleDTOS = new ArrayList<ScheduleDTO>();

        for (Object[] schedule:schedulelist) {
            String traincode = (String) schedule[0];
            Date depdate = (Date) schedule[1];
            String arstname = (String) schedule[2];
            Date ardate = (Date) schedule[3];
            String depstname = (String) schedule[4];
            scheduleDTOS.add(new ScheduleDTO(traincode,depdate,arstname,ardate,depstname));

        }

        return scheduleDTOS;

    }

    public List<ScheduleDTO> getTrainTariff(String traincode, String departuredate) {

        System.out.println("ScheduleService getTrainTariff is called");
        String query = "select new ru.novand.orientexpress.domain.dto.ScheduleDTO(tr.train.trainCode,tr.arrivalstation.stationname,tr.departurestation.stationname, tr.tariff) from TrainTariff tr where tr.train.trainCode = :traincode";
        List<ScheduleDTO> scheduleDTOS = entityManager.createQuery(query)
                .setParameter("traincode", traincode)
                .getResultList();
        return scheduleDTOS;
    }

    public List<ScheduleDTO> getTariffOrdered( List<ScheduleDTO> tariff,String fromSt, String toSt) {

        List<ScheduleDTO> tariffOrdered = new ArrayList<>();

        String prevVertex = null;
        String curVertex = fromSt;
        boolean flag=true;

        while (flag)
        {
            for(ScheduleDTO aSiteId: tariff) {
                if (curVertex.equals(aSiteId.getDepstationname()))
                {
                    prevVertex = curVertex;
                    curVertex = aSiteId.getArrstationname();

                    tariffOrdered.add(new ScheduleDTO("",prevVertex,curVertex, aSiteId.getDoubleIterator()));

                    if (curVertex.equals(toSt))
                    {
                        flag = false;
                        break;
                    }
                    continue;
                }
            }
            if (curVertex.equals(toSt)) flag = false;
        }
        return tariffOrdered;
    }

    public List<String> getCityList( List<ScheduleDTO> tariffOrdered) {

        List<String> citylist = new ArrayList<>();

        for (ScheduleDTO scheduleDTO:tariffOrdered) {
            citylist.add(scheduleDTO.getArrstationname());
        }
        return citylist;
    }

    public BigDecimal getTariff( List<ScheduleDTO> tariffOrdered) {

        BigDecimal tariffvalue = BigDecimal.valueOf(0);

        for (ScheduleDTO scheduleDTO:tariffOrdered) {
            tariffvalue = scheduleDTO.getDoubleIterator().add(tariffvalue);
        }
        return tariffvalue;
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public List<ScheduleDTO> getScheduleByStation(String stationname, String departuredate) {
        List<Schedule> scheduleList = scheduleDAO.getTrains(stationname);
        List<TrainScheduleDates> trainscheduledatesList = trainScheduleDatesDAO.findAll();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        LocalDateTime ld;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime departureDateFormat = LocalDateTime.parse(departuredate+" 00:00", formatter);

        for (Schedule schedule:scheduleList) {
            for (TrainScheduleDates trainScheduleDates:trainscheduledatesList) {
                if (trainScheduleDates.getTrain().getTrainCode().equals(schedule.getTrain().getTrainCode()))
                {
                    TrainRoute trainRoute = trainRouteDAO.findByTrainCode(trainScheduleDates.getTrain().getTrainCode());
                    ld = trainScheduleDates.getDeparturedate().atStartOfDay();
                    ld.plusHours(schedule.getInterval());
                    if(ld.isEqual(departureDateFormat)) {
                        scheduleDTOList.add(new ScheduleDTO(schedule.getTrain().getTrainCode(), convertToDateViaInstant(ld),trainRoute.getArrivalstation().getStationname(),trainRoute.getDeparturestation().getStationname()));
                    }
                }
            }
        }

        return scheduleDTOList;
    }

    public void delete(int id) {
        Schedule schedule = scheduleDAO.findById(id);
        if (schedule!=null) scheduleDAO.delete(schedule);
    }

    public void save(String fromst,String tost, String interval, int id) {

        Schedule schedule = new Schedule();
        schedule.setInterval(Integer.parseInt(interval));
        Station station = stationDAO.getStation(fromst);
        schedule.setArrivalstation(station);
        station = stationDAO.getStation(tost);
        schedule.setDeparturestation(station);
        scheduleDAO.save(schedule);
    }

    public void update(String fromst,String tost, String interval, int id) {

        Schedule schedule = scheduleDAO.findById(id);
        schedule.setInterval(Integer.parseInt(interval));
        Station station = stationDAO.getStation(fromst);
        schedule.setArrivalstation(station);
        station = stationDAO.getStation(tost);
        schedule.setDeparturestation(station);
        scheduleDAO.update(schedule);
    }

    public Schedule get(int id) {

        return scheduleDAO.findById(id);
    }
}
