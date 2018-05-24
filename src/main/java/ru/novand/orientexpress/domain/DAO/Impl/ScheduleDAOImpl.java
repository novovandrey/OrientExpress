package ru.novand.orientexpress.domain.DAO.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.novand.orientexpress.domain.DAO.interfaces.ScheduleDAO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.exception.CustomSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
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

    @Override
    public Schedule save(Schedule entity) {

        try {
            manager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomSQLException("Can not save TrainScheduleDates: " + ex);
        }
        return entity;

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
    public Schedule findById(int id) {
        return manager.find(Schedule.class, id);
    }
}
