package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TRAINSCHEDULEDATES")
@NamedQuery(name = "TrainScheduleDates.findAll", query = "FROM TrainScheduleDates")
public class TrainScheduleDates implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINSCHEDULE_ID")
    private int idtrainschedule;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID")
    private Train train;

    @NotEmpty
    @Column(name = "DEPARTUREDATE")
    private LocalDate departuredate;

    public int getIdtrainschedule() {
        return idtrainschedule;
    }

    public void setIdtrainschedule(int idtrainschedule) {
        this.idtrainschedule = idtrainschedule;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(LocalDate departuredate) {
        this.departuredate = departuredate;
    }

    public TrainScheduleDates() {
    }
}
