package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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
    private Instant departuredate;

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

    public Instant getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Instant departuredate) {
        this.departuredate = departuredate;
    }

    public TrainScheduleDates() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainScheduleDates that = (TrainScheduleDates) o;
        return Objects.equals(train, that.train) &&
                Objects.equals(departuredate, that.departuredate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(train, departuredate);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainScheduleDates{");
        sb.append("idtrainschedule=").append(idtrainschedule);
        sb.append(", train=").append(train);
        sb.append(", departuredate=").append(departuredate);
        sb.append('}');
        return sb.toString();
    }
}
