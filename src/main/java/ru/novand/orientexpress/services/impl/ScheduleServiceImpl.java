package ru.novand.orientexpress.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.novand.orientexpress.domain.DAO.interfaces.*;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.dto.StationDTO;
import ru.novand.orientexpress.domain.entities.*;
import ru.novand.orientexpress.exception.CustomSQLException;
import ru.novand.orientexpress.mappers.TrainTariffToScheduleDTOMapper;
import ru.novand.orientexpress.services.interfaces.ScheduleService;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional //need to update\delete queries.

public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private TrainScheduleDatesDAO trainScheduleDatesDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private TrainRouteDAO trainRouteDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private TrainTariffDAO trainTariffDAO;

    private TrainTariffToScheduleDTOMapper trainTariffToScheduleDTOMapper;

    public List<Station> getAllStations() {

        try {
            List<Station> list = stationDAO.findAll();
            return list;
        } catch (RuntimeException ex) {
            throw new CustomSQLException("Unable to find stations!", ex);
        }
    }


    public List<ScheduleDTO> getSchedule(String fromSt, String toSt, String departuredate) {
        System.out.println("ScheduleService getSchedule is called");

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusDays(1);

        List<ScheduleDTO> schedulelist = scheduleDAO.getSchedule(fromSt,  toSt,  departuredate);

        return schedulelist;

    }

    public List<ScheduleDTO> getTrainTariff(String traincode, String departuredate) {

        System.out.println("ScheduleService getTrainTariff is called");
        List<TrainTariff> trainTariffList = trainTariffDAO.getAllTrainTariffByTrainCode(traincode);
        List<ScheduleDTO> scheduleDTOList=new ArrayList<>();
        for ( TrainTariff trainTariff : trainTariffList ) {
            scheduleDTOList.add( TrainTariffToScheduleDTOMapper.INSTANCE.trainTariffToScheduleDTO ( trainTariff ) );
        }
        return scheduleDTOList;
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

        if(tariffOrdered.size()!=0) {
            citylist.add(tariffOrdered.get(0).getDepstationname());
            for (ScheduleDTO scheduleDTO : tariffOrdered) {
                citylist.add(scheduleDTO.getArrstationname());
            }
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

    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.of("UTC"))
                        .toInstant());
    }

    public List<ScheduleDTO> getScheduleByStation(String stationname, String departuredate) {
        List<Schedule> scheduleList = scheduleDAO.getTrains(stationname);
        List<TrainScheduleDates> trainscheduledatesList = trainScheduleDatesDAO.findAll();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        LocalDateTime ld;
        LocalDate localDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate departureDateFormat = LocalDate.parse(departuredate, formatter);

        for (Schedule schedule:scheduleList) {
            for (TrainScheduleDates trainScheduleDates:trainscheduledatesList) {
                if (trainScheduleDates.getTrain().getTrainCode().equals(schedule.getTrain().getTrainCode()))
                {
                    TrainRoute trainRoute = trainRouteDAO.findByTrainCode(trainScheduleDates.getTrain().getTrainCode());
                    ld = LocalDateTime.ofInstant(trainScheduleDates.getDeparturedate(), ZoneOffset.UTC);
                    int interval = schedule.getInterval();
                    ld = ld.plusMinutes(interval);
                    localDate = ld.toLocalDate();
                    if(localDate.isEqual(departureDateFormat)) {
                        scheduleDTOList.add(new ScheduleDTO(schedule.getTrain().getTrainCode(), ld,trainRoute.getArrivalstation().getStationname(),trainRoute.getDeparturestation().getStationname()));
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

    public Schedule save(String fromst,String tost, String interval,String traincode) {

        Schedule schedule = new Schedule();
        schedule.setInterval(Integer.parseInt(interval));
        Station station = stationDAO.getStation(fromst);
        schedule.setArrivalstation(station);
        station = stationDAO.getStation(tost);
        schedule.setDeparturestation(station);
        Train train = trainDAO.findByCode(traincode);
        schedule.setTrain(train);
        Schedule result = scheduleDAO.save(schedule);
        return result;
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
