package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Size(min = 5, max = 255)
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

    public Station(String stationname) {
        this.stationname = stationname;
    }

    public Station() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Station{");
        sb.append("idStation=").append(idStation);
        sb.append(", stationname='").append(stationname).append('\'');
        sb.append(", arrschedulelist=").append(arrschedulelist);
        sb.append(", depschedulelist=").append(depschedulelist);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return idStation == station.idStation &&
                Objects.equals(stationname, station.stationname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStation, stationname);
    }
}
