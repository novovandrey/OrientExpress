package ru.novand.OrientExpress.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRAINROUTE")
public class TrainRoute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAINROUTE_ID")
    private int idtrainroute;

    @OneToOne(mappedBy = "trainRoute", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "FROMSTATION")
    private Station arrivalstation;

    @ManyToOne
    @JoinColumn(name = "TOSTATION")
    private Station departurestation;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getIdtrainschedule() {
        return idtrainroute;
    }

    public void setIdtrainschedule(int idtrainroute) {
        this.idtrainroute = idtrainroute;
    }

    public int getIdtrainroute() {
        return idtrainroute;
    }

    public void setIdtrainroute(int idtrainroute) {
        this.idtrainroute = idtrainroute;
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
}
