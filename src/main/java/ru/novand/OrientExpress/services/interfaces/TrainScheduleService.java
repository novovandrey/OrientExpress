package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.TrainScheduleDates;

import java.util.List;

public interface TrainScheduleService {
    public TrainScheduleDates addTrainSchedule(String traincode, String departuredate);
    public List<TrainScheduleDates> getTrainScheduleList();
}
