package ru.novand.orientexpress.domain.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRAINROUTE")
@NamedQueries({
        @NamedQuery(name = "TrainRoute.findAll", query = "FROM TrainRoute"),
        @NamedQuery(name = "TrainRoute.find", query = "FROM TrainRoute where idTrainRoute = :trainRouteID"),
        @NamedQuery(name = "TrainRoute.findByTrainCode", query = "FROM TrainRoute where train.trainCode = :traincode")
})
public class TrainRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINROUTE_ID")
    private int idTrainRoute;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "FROMSTATION")
    private Station arrivalstation;

    @ManyToOne
    @JoinColumn(name = "TOSTATION")
    private Station departurestation;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    public TrainRoute() {
    }

    public int getIdTrainRoute() {
        return idTrainRoute;
    }

    public void setIdTrainRoute(int idTrainRoute) {
        this.idTrainRoute = idTrainRoute;
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

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idTrainRoute", idTrainRoute)
                .append("train", train)
                .append("arrivalstation", arrivalstation)
                .append("departurestation", departurestation)
                .append("scheduleList", scheduleList)
                .toString();
    }
}
