package ru.novand.orientexpress.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class TrainDTO {

    @NotEmpty(message = "{user.email.invalid}")
    private String traincode;

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainDTO{");
        sb.append(", traincode='").append(traincode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
