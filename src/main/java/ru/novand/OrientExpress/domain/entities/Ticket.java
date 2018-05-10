package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TICKET")
@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "FROM Ticket"),
        @NamedQuery(name = "Ticket.find", query = "FROM Ticket where idTicket = :ticketId"),
        @NamedQuery(name = "Ticket.findTicketsByTrainCode", query = "FROM Ticket where trainCode = :trainCode and departuredate=:departuredate")
})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDTICKET")
    private int idTicket;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passenger passenger;

    @Size(min = 0, max = 255)
    @Column(name = "TRAINCODE")
    private String trainCode;

    @NotEmpty
    @Column(name = "DEPARTUREDATE")
    private Date departuredate;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public Ticket() {
    }

    public Ticket(Passenger passenger, String trainCode, Date departuredate) {
        this.passenger = passenger;
        this.trainCode = trainCode;
        this.departuredate = departuredate;
    }
}