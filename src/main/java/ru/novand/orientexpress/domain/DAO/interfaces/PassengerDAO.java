package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.Passenger;

import java.util.List;

public interface PassengerDAO {

    public Passenger save(Passenger entity);

    public List<Passenger> findAll();

    public Passenger delete(Passenger entity);
    public Passenger update(Passenger entity);
    public Passenger findById(int id);
    public Passenger findByUserId(int userid);
    public Passenger findByUserName(String username);
}
