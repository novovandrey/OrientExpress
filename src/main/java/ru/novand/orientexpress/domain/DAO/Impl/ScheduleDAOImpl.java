package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.orientexpress.domain.DAO.interfaces.ScheduleDAO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("scheduleDAO")
public class ScheduleDAOImpl implements ScheduleDAO {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Schedule> getTrains(String stationName) {
        List<Schedule> result = null;
        try {
            TypedQuery<Schedule> query = manager.createNamedQuery("Schedule.GetAllTrainsByStName", Schedule.class);
            query.setParameter("stationName", stationName);
            result = query.getResultList();
        } catch (PersistenceException ex) {
            throw new CustomSQLException(ex.getMessage());
        }
        return result;
    }

    @Transactional
    @Override
    public Schedule save(Schedule schedule) {

        try {
            manager.persist(schedule);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save TrainScheduleDates: " + ex);
        }
        return schedule;

    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> result = null;
        TypedQuery<Schedule> query = manager.createNamedQuery("Schedule.GetAllScheduleList", Schedule.class);
        result = query.getResultList();
        return result;
    }

    void flushAndClear() {
        manager.flush();
        manager.clear();
    }

    @Override
    @Transactional
    public Schedule delete(Schedule entity) {
        try {
            Schedule schedule = manager.find(Schedule.class, entity.getSchedule_id());
            manager.remove(schedule);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not remove schedule: " + ex);
        }
        return entity;
    }

    @Transactional
    @Override
    public Schedule update(Schedule entity) {
        try {
            manager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not update schedule: " + ex);
        }
        return entity;
    }

    @Override
    @Transactional
    public List<Schedule> getAllScheduleByTrainCode(String traincode) {

        List<Schedule> result = null;
        TypedQuery<Schedule> query = manager.createNamedQuery("Schedule.GetAllScheduleByTrainCode", Schedule.class);
        query.setParameter("trainCode", traincode);
        result = query.getResultList();
        return result;
    }

    @Override
    public List<ScheduleDTO> getSchedule(String fromSt, String toSt, String departuredate) {

        String query ="SELECT tr.TRAINCODE, date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE ), st1.STATIONNAME as st1,date_add(d1.DEPARTUREDATE,INTERVAL s2.INTERVAL_M MINUTE ), st2.STATIONNAME  as st2 FROM Schedule s1 join Schedule s2 on s1.TRAIN_ID = s2.TRAIN_ID join TrainScheduleDates d1 on s1.TRAIN_ID = d1.TRAIN_ID join Station st1 on st1.STATION_ID = s1.DEPARTURE_STATION_ID join Station st2 on st2.STATION_ID = s2.DEPARTURE_STATION_ID join Train tr on tr.TRAIN_ID = s1.TRAIN_ID where st1.STATIONNAME = '#fromSt' and st2.STATIONNAME = '#toSt' and date(date_add(d1.DEPARTUREDATE,INTERVAL s1.INTERVAL_M MINUTE )) = date(STR_TO_DATE('#date','%d.%m.%Y'))";
        query = query.replaceAll("#date",departuredate);
        query = query.replaceAll("#toSt",toSt);
        query = query.replaceAll("#fromSt",fromSt);
        List<Object[]> schedulelist = manager.createNativeQuery(query)
                .getResultList();


        List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();

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


    @Override
    @Transactional
    public Schedule findById(int id) {
        return manager.find(Schedule.class, id);
    }
}
