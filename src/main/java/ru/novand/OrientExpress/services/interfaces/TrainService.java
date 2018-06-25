package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.Train;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface TrainService {
    public Train addTrain(String trainname, String traincode, int seatsnumber);
    public List<Train> getAllTrains();
    public void sendMessage(int id) ;
}
