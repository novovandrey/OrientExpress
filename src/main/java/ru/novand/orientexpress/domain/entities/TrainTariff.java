package ru.novand.orientexpress.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TRAINTARIFF")
public class TrainTariff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TARIFF_ID")
    private int tariff_id;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_STATION_ID")
    private Station arrivalstation;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_STATION_ID")
    private Station departurestation;

    @Column(name = "TARIFF")
    private BigDecimal tariff;

    public int getTariff_id() {
        return tariff_id;
    }

    public void setTariff_id(int tariff_id) {
        this.tariff_id = tariff_id;
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

    public BigDecimal getTariff() {
        return tariff;
    }

    public void setTariff(BigDecimal tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "TrainTariff{" +
                "tariffid=" + tariff_id +
                ", traincode='" + train.getTrainCode() + '\'' +
                '}';
    }
}
