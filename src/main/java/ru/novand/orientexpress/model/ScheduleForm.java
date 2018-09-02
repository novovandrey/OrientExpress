package ru.novand.orientexpress.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ScheduleForm {

    @Size(max = 1, min = 1, message="Please select a password")
    private String fromSt;
    @Size(max = 2, min = 1, message="Please select a password")
    private String toSt;
    @NotNull(message="Please select a password")
    private String departuredate;

    public String getFromSt() {
        return fromSt;
    }

    public void setFromSt(String fromSt) {
        this.fromSt = fromSt;
    }

    public String getToSt() {
        return toSt;
    }

    public void setToSt(String toSt) {
        this.toSt = toSt;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }
}
