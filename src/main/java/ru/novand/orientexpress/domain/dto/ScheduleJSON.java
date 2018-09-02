package ru.novand.orientexpress.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class ScheduleJSON {

    private String traincode;
    private Date departuredate;
    private String depstationname;
    private Date arrivaldate;
    private String arrstationname;
    private BigDecimal doubleIterator;
    //todo camelCase

    public BigDecimal getDoubleIterator() {
        return doubleIterator;
    }

    public void setDoubleIterator(BigDecimal doubleIterator) {

        this.doubleIterator = doubleIterator;
    }

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getDepstationname() {
        return depstationname;
    }

    public void setDepstationname(String depstationname) {
        this.depstationname = depstationname;
    }

    public String getArrstationname() {
        return arrstationname;
    }

    public void setArrstationname(String arrstationname) {
        this.arrstationname = arrstationname;
    }

    public ScheduleJSON() {
    }

    public ScheduleJSON(String traincode, LocalDateTime depdate, String depstationname, LocalDateTime ardate, String arrstationname, BigDecimal iterator) {

        this.traincode = traincode;
        if(depdate!=null) this.departuredate = java.sql.Timestamp.valueOf(depdate);
        this.depstationname = depstationname;
        if(ardate!=null) this.arrivaldate = java.sql.Timestamp.valueOf(ardate);
        this.arrstationname = arrstationname;
        this.doubleIterator = iterator;
    }

    public ScheduleJSON(String traincode, Date ardate, String from, String to) {
        this.traincode = traincode;
        this.arrivaldate = ardate;
        this.depstationname = from;
        this.arrstationname = to;
    }

    public ScheduleJSON(String traincode, String depstationname, String arrstationname, BigDecimal iterator) {
        this.traincode = traincode;
        this.depstationname = depstationname;
        this.arrstationname = arrstationname;
        this.doubleIterator = iterator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleJSON that = (ScheduleJSON) o;
        return Objects.equals(traincode, that.traincode) &&
                Objects.equals(departuredate, that.departuredate) &&
                Objects.equals(depstationname, that.depstationname) &&
                Objects.equals(arrivaldate, that.arrivaldate) &&
                Objects.equals(arrstationname, that.arrstationname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(traincode, departuredate, depstationname, arrivaldate, arrstationname);
    }

    @Override
    public String toString() {
        return "ScheduleJSON{" +
                "traincode=" + traincode +
                ", departuredate='" + departuredate +
                ", depstationname='" + depstationname +
                ", arrivaldate='" + arrivaldate +
                ", arrstationname='" + arrstationname +
                ", doubleIterator='" + doubleIterator +
                '}';
    }
}