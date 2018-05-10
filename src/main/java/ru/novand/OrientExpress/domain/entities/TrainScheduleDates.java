package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TRAINSCHEDULEDATES")
public class TrainScheduleDates implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINSCHEDULE_ID")
    private int idtrainschedule;

    @Column(name = "TRAIN_ID")
    private int idTrain;

    @NotEmpty
    @Column(name = "DEPARTUREDATE")
    private Date departuredate;

    public int getIdtrainschedule() {
        return idtrainschedule;
    }

    public void setIdtrainschedule(int idtrainschedule) {
        this.idtrainschedule = idtrainschedule;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }
}
