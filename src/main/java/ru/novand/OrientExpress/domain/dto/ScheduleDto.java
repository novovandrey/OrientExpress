package ru.novand.OrientExpress.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class ScheduleDto {

    private String traincode;
    private Date departuredate;
    private String depstationname;
    private Date arrivaldate;
    private String arrstationname;

    public int getIntegerIterator() {
        return integerIterator;
    }

    public void setIntegerIterator(int integerIterator) {
        this.integerIterator = integerIterator;
    }

    private int integerIterator;

    public BigDecimal getDoubleIterator() {
        return doubleIterator;
    }

    public void setDoubleIterator(BigDecimal doubleIterator) {
        this.doubleIterator = doubleIterator;
    }

    private BigDecimal doubleIterator;

//    public Date getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Date depdate,Date arrdate) {
//        Period p = new Period(depdate, arrdate);
//        int hours = p.getHours();
//    }

//    private Date duration;

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

    public String getDepstationname() {
        return depstationname;
    }

    public void setDepstationname(String depstationname) {
        this.depstationname = depstationname;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getArrstationname() {
        return arrstationname;
    }

    public void setArrstationname(String arrstationname) {
        this.arrstationname = arrstationname;
    }

    public ScheduleDto(String traincode, Date depdate, String depstationname, Date ardate, String arrstationname) {
        this.traincode = traincode;
        this.departuredate = depdate;
        this.depstationname = depstationname;
        this.arrivaldate = ardate;
        this.arrstationname = arrstationname;
    }

    public ScheduleDto(String traincode, Date ardate) {
        this.traincode = traincode;
        this.arrivaldate = ardate;
    }

    public ScheduleDto(String traincode, String depstationname, String arrstationname, int iterator) {
        this.traincode = traincode;
        this.depstationname = depstationname;
        this.arrstationname = arrstationname;
        this.integerIterator = iterator;
    }

    public ScheduleDto(String traincode, String depstationname, String arrstationname, BigDecimal iterator) {
        this.traincode = traincode;
        this.depstationname = depstationname;
        this.arrstationname = arrstationname;
        this.doubleIterator = iterator;
    }


}