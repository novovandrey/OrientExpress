package ru.novand.orientexpress.services.interfaces;

import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.exception.CustomSQLException;

import java.time.Instant;
import java.util.List;

public interface PassengerService {
    public Passenger getPassengerByUserName(String username);

    public List<Passenger> getAllPassengers(String trainCode, Instant depdate);

    public List<Train> getAllTrains();
}