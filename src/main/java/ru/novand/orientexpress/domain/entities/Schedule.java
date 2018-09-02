package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SCHEDULE")
@NamedQueries({
        @NamedQuery(name = "Schedule.GetAllScheduleList", query = "FROM Schedule"),
        @NamedQuery(name = "Schedule.GetScheduleByID", query = "FROM Schedule where schedule_id = :schedule_id"),
        @NamedQuery(name = "Schedule.GetAllTrainsByStName", query = "FROM Schedule where departurestation.stationname in (:stationName)"),
        @NamedQuery(name = "Schedule.GetAllScheduleByTrainCode", query = "FROM Schedule where train.trainCode in (:trainCode)")
})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SCHEDULE_ID")
    private int schedule_id;

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

    public Schedule() {}

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

    public Schedule(String schedulename, Train train, Station arrivalstation, Station departurestation, int interval) {
        this.schedulename = schedulename;
        this.train = train;
        this.arrivalstation = arrivalstation;
        this.departurestation = departurestation;
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return interval == schedule.interval &&
                Objects.equals(schedulename, schedule.schedulename) &&
                Objects.equals(train, schedule.train) &&
                Objects.equals(arrivalstation, schedule.arrivalstation) &&
                Objects.equals(departurestation, schedule.departurestation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(schedulename, train, arrivalstation, departurestation, interval);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Schedule{");
        sb.append("schedule_id=").append(schedule_id);
        sb.append(", schedulename='").append(schedulename).append('\'');
        sb.append(", train=").append(train);
        sb.append(", arrivalstation=").append(arrivalstation);
        sb.append(", departurestation=").append(departurestation);
        sb.append(", interval=").append(interval);
        sb.append('}');
        return sb.toString();
    }
}
