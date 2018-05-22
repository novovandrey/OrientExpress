package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "SCHEDULE")
@NamedQueries({
        @NamedQuery(name = "Schedule.GetAllScheduleList", query = "FROM Schedule"),
        @NamedQuery(name = "Schedule.GetScheduleByID", query = "FROM Schedule where schedule_id = :schedule_id"),
        @NamedQuery(name = "Schedule.GetAllTrainsByStName", query = "FROM Schedule where departurestation.stationname in (:stationName)")
})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private int schedule_id;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "SCHEDULENAME")
    private String schedulename;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_STATION_ID")
    private Station arrivalstation;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_STATION_ID")
    private Station departurestation;

    @Column(name = "INTERVAL_M")
    private int interval;

    public Schedule() {
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSchedulename() {
        return schedulename;
    }

    public void setSchedulename(String schedulename) {
        this.schedulename = schedulename;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getArrivalstation() {
        return arrivalstation;
    }

    public void setArrivalstation(Station arrivalstation) {
        this.arrivalstation = arrivalstation;
    }

    public Station getDeparturestation() {
        return departurestation;
    }

    public void setDeparturestation(Station departurestation) {
        this.departurestation = departurestation;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "User{" +
                "schedule_id=" + schedule_id +
                ", schedulename='" + schedulename + '\'' +
                '}';
    }
}
