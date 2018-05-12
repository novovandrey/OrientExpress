package ru.novand.OrientExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.OrientExpress.domain.DAO.interfaces.StationDAO;
import ru.novand.OrientExpress.domain.DAO.interfaces.TrainScheduleDatesDAO;
import ru.novand.OrientExpress.domain.dto.ScheduleDto;
import ru.novand.OrientExpress.domain.entities.*;
import ru.novand.OrientExpress.exception.CustomSQLException;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
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

    public List<ScheduleDto> GetSchedule(String fromSt, String toSt, String departuredate, String departuretime) {
        System.out.println("ScheduleService GetSchedule is called");

        int toStInt = 1;
        //TODO localdadtetime
        Date departuredateDate = new Date();
        try {
            departuredateDate=new SimpleDateFormat("yyyy-MM-dd").parse(departuredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // parse input date
        //TODO timezones store UTC
        LocalDateTime localDateTime = LocalDateTime.now();// departuredateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(1);
        Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

//        String query = "select new " +
//            "   ru.novand.OrientExpress.domain.dto.ScheduleDto(" +
//            "       s2.train.trainCode, " +
//            "       s2.arrivalstation.stationname," +
//            "       s2.departurestation.stationname" +
//            "   ) " +
//            "from Schedule AS s2, TrainScheduleDates AS d1, Schedule AS s1 " +
//            " where s1.train.idTrain = s2.train.idTrain and s2.train.idTrain = d1.idTrain   ";

//        List<ScheduleDto> postDTOs = entityManager
//                .createQuery(query, ScheduleDto.class)
////                .setParameter("fromSt", fromSt)
////                .setParameter("toSt", toSt)
////                .setParameter("date2",date2, TemporalType.DATE)
////                .setParameter("date1", departuredateDate, TemporalType.DATE)
//                .getResultList();

        //TYPEDQUERY

        String query ="SELECT tr.TRAINCODE, date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE ), st1.STATIONNAME as st1,date_add(d1.DEPARTUREDATE,INTERVAL s2.INTERVAL_M MINUTE ), st2.STATIONNAME  as st2 FROM Schedule s1 join Schedule s2 on s1.TRAIN_ID = s2.TRAIN_ID join TrainScheduleDates d1 on s1.TRAIN_ID = d1.TRAIN_ID join Station st1 on st1.STATION_ID = s1.ARRIVAL_STATION_ID join Station st2 on st2.STATION_ID = s2.DEPARTURE_STATION_ID join Train tr on tr.TRAIN_ID = s1.TRAIN_ID where st1.STATIONNAME = '#fromSt' and st2.STATIONNAME = '#toSt' and date(date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE )) = date(STR_TO_DATE('#date','%Y-%m-%d'))";
        query = query.replaceAll("#date",departuredate);
        query = query.replaceAll("#toSt",toSt);
        query = query.replaceAll("#fromSt",fromSt);
        //tr.TRAINCODE, date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE ), st1.STATIONNAME, date_add(d1.DEPARTUREDATE,INTERVAL s2.INTERVAL_M MINUTE ), st2.STATIONNAME
        List<Object[]> schedulelist = entityManager.createNativeQuery(query)
                .getResultList();

        ArrayList scheduleDTOS = new ArrayList<ScheduleDto>();

        for (Object[] schedule:schedulelist) {
            String traincode = (String) schedule[0];
            Date depdate = (Date) schedule[1];
            String arstname = (String) schedule[2];
            Date ardate = (Date) schedule[3];
            String depstname = (String) schedule[4];
            scheduleDTOS.add(new ScheduleDto(traincode,depdate,arstname,ardate,depstname));

        }

        return scheduleDTOS;

    }

    public List<TrainRoute> GetTrainRoute(String traincode, String departuredate) {

        System.out.println("ScheduleService GetTrainRoute is called");
        String query = "from TrainRoute where train.trainCode = :traincode";
        TypedQuery<TrainRoute> typedQuery = entityManager.createQuery(query, TrainRoute.class);
        return typedQuery.setParameter("traincode", traincode).getResultList();
    }

    public List<ScheduleDto> GetTrainTariff(String traincode, String departuredate) {

        System.out.println("ScheduleService GetTrainTariff is called");
        String query = "select new ru.novand.OrientExpress.domain.dto.ScheduleDto(tr.train.trainCode,tr.arrivalstation.stationname,tr.departurestation.stationname, tr.tariff) from TrainTariff tr where tr.train.trainCode = :traincode";
        List<ScheduleDto> scheduleDTOS = entityManager.createQuery(query)
                .setParameter("traincode", traincode)
                .getResultList();
        return scheduleDTOS;
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public List<ScheduleDto> GetScheduleByStation(String stationname) {
        List<Schedule> scheduleList = stationDAO.getTrains(stationname);
        List<TrainScheduleDates> trainscheduledatesList = trainScheduleDatesDAO.findAll();
        List<ScheduleDto> scheduleDTOList = new ArrayList<ScheduleDto>();
        LocalDateTime ld;

        for (Schedule schedule:scheduleList) {
            for (TrainScheduleDates trainScheduleDates:trainscheduledatesList) {
                if (trainScheduleDates.getTrain().getTrainCode().equals(schedule.getTrain().getTrainCode()))
                {
                    Instant instant = trainScheduleDates.getDeparturedate().toInstant();
                    ld =instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                    ld.plusHours(schedule.getInterval());
                    scheduleDTOList.add(new ScheduleDto(schedule.getTrain().getTrainCode(),convertToDateViaInstant(ld)));
                }
            }
        }

        return scheduleDTOList;
    }

}
