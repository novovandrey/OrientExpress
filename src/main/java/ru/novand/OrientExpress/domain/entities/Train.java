package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRAIN")
@NamedQueries({
        @NamedQuery(name = "Train.findAll", query = "FROM Train"),
        @NamedQuery(name = "Train.find", query = "FROM Train where idTrain = :trainId"),
        @NamedQuery(name = "Train.findByCode", query = "FROM Train where trainCode = :trainCode")
})
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAIN_ID")
    private int idTrain;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "TRAINNAME")
    private String trainname;

    @Size(min = 0, max = 255)
    @Column(name = "TRAINCODE")
    private String trainCode;

    @Column(name = "SEATSNUMBER")
    private int trainSeats;

    //TODO удалить
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAIN_ID")
    private TrainRoute trainRoute;

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public int getTrainSeats() {
        return trainSeats;
    }

    public void setTrainSeats(int trainSeats) {
        this.trainSeats = trainSeats;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "User{" +
                "idTrain=" + idTrain +
                ", trainname='" + trainname + '\'' +
                '}';
    }
}
