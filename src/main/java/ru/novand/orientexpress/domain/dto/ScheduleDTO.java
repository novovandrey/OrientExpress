package ru.novand.orientexpress.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class ScheduleDTO {

    private String traincode;
    private LocalDateTime departuredate;
    private String depstationname;
    private LocalDateTime arrivaldate;
    private String arrstationname;
    //todo camelCase

    public BigDecimal getDoubleIterator() {
        return doubleIterator;
    }

    public void setDoubleIterator(BigDecimal doubleIterator) {

        this.doubleIterator = doubleIterator;
    }

    private BigDecimal doubleIterator;

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public LocalDateTime getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(LocalDateTime departuredate) {
        this.departuredate = departuredate;
    }

    public void setArrivaldate(LocalDateTime arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public LocalDateTime getArrivaldate() {
        return arrivaldate;
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

    public ScheduleDTO() {
    }

    public ScheduleDTO(String traincode, LocalDateTime depdate, String depstationname, LocalDateTime ardate, String arrstationname) {
        this.traincode = traincode;
        this.departuredate = depdate;
        this.depstationname = depstationname;
        this.arrivaldate = ardate;
        this.arrstationname = arrstationname;
    }

    public ScheduleDTO(String traincode, LocalDateTime ardate, String from, String to) {
        this.traincode = traincode;
        this.arrivaldate = ardate;
        this.depstationname = from;
        this.arrstationname = to;
    }
//
//    public ScheduleDTO(String traincode, String depstationname, String arrstationname, int iterator) {
//        this.traincode = traincode;
//        this.depstationname = depstationname;
//        this.arrstationname = arrstationname;
//        this.integerIterator = iterator;
//    }

    public ScheduleDTO(String traincode, String depstationname, String arrstationname, BigDecimal iterator) {
        this.traincode = traincode;
        this.depstationname = depstationname;
        this.arrstationname = arrstationname;
        this.doubleIterator = iterator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDTO that = (ScheduleDTO) o;
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
        return "ScheduleDTO{" +
                "traincode=" + traincode +
                ", departuredate='" + departuredate +
                ", depstationname='" + depstationname +
                ", arrivaldate='" + arrivaldate +
                ", arrstationname='" + arrstationname +
                ", doubleIterator='" + doubleIterator +
                '}';
    }
}