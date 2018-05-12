package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class. It store Station list in database.
 *
 * @autor Andrey Novov
 * @version 1.0
 * @since version 1.0
 */

@Entity
@Table(name = "STATION")
@NamedQueries({
        @NamedQuery(name = "Station.GetAllStationList", query = "FROM Station"),
        @NamedQuery(name = "Station.GetStationByName", query = "FROM Station where stationname = :stationName")
})
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATION_ID")
    private int idStation;

    @NotEmpty
    @Size(min = 5, max = 20)
    @Column(name = "STATIONNAME")
    private String stationname;

    @OneToMany(mappedBy = "arrivalstation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> arrschedulelist = new ArrayList<>();

    @OneToMany(mappedBy = "departurestation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> depschedulelist = new ArrayList<>();

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public List<Schedule> getArrschedulelist() {
        return arrschedulelist;
    }

    public void setArrschedulelist(List<Schedule> arrschedulelist) {
        this.arrschedulelist = arrschedulelist;
    }

    public List<Schedule> getDepschedulelist() {
        return depschedulelist;
    }

    public void setDepschedulelist(List<Schedule> depschedulelist) {
        this.depschedulelist = depschedulelist;
    }

    @Override
    public String toString() {
        return "User{" +
                "idStation=" + idStation +
                ", stationname='" + stationname + '\'' +
                '}';
    }
}
