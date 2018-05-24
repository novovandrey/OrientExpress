package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.entities.Station;

public interface StationDAO extends GenDAO<Station> {

    public Station getStation(String name);

}