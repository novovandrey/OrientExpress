package ru.novand.orientexpress.domain.entities;

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

    @Size(min = 0, max = 255)
    @Column(name = "TRAINNAME")
    private String trainname;

    @Size(min = 0, max = 5, message = "{train.traincode.empty}")
    @Column(name = "TRAINCODE")
    private String trainCode;

    @Column(name = "SEATSNUMBER")
    private int trainSeats;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainScheduleDates> trainScheduleDates = new ArrayList<>();

    public Train(String trainname, String trainCode, int trainSeats) {
        this.trainname = trainname;
        this.trainCode = trainCode;
        this.trainSeats = trainSeats;
    }

    public Train() {
    }

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

    public List<TrainScheduleDates> getTrainScheduleDates() {
        return trainScheduleDates;
    }

    public void setTrainScheduleDates(List<TrainScheduleDates> trainScheduleDates) {
        this.trainScheduleDates = trainScheduleDates;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Train)) {
            return false;
        }

        Train train = (Train) o;

        return train.getIdTrain()==getIdTrain();
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getIdTrain();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Train{");
        sb.append("trainname='").append(trainname).append('\'');
        sb.append(", trainCode='").append(trainCode).append('\'');
        sb.append(", trainSeats=").append(trainSeats);
        sb.append('}');
        return sb.toString();
    }
}
