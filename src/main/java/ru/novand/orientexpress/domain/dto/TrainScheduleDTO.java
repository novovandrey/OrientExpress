package ru.novand.orientexpress.domain.dto;

public class TrainScheduleDTO {
    private String fromst;
    private String tost;
    private String interval;

    public String getFromst() {
        return fromst;
    }

    public void setFromst(String fromst) {
        this.fromst = fromst;
    }

    public String getTost() {
        return tost;
    }

    public void setTost(String tost) {
        this.tost = tost;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
