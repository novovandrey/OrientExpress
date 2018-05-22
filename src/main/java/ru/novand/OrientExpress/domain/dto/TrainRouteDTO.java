package ru.novand.OrientExpress.domain.dto;

public class TrainRouteDTO {
    private String traincode;
    private String arrst;
    private String depst;

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public String getArrst() {
        return arrst;
    }

    public void setArrst(String arrst) {
        this.arrst = arrst;
    }

    public String getDepst() {
        return depst;
    }

    public void setDepst(String depst) {
        this.depst = depst;
    }
}
