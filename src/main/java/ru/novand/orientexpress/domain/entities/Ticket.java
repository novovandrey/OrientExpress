package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passenger passenger;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "TRAINCODE")
    private String trainCode;

    @NotNull
    @Column(name = "DEPARTUREDATE")
    private Instant departuredate;

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

    public Instant getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Instant departuredate) {
        this.departuredate = departuredate;
    }

    public Ticket() {
    }

    public Ticket(Passenger passenger, String trainCode, Instant departuredate) {
        this.passenger = passenger;
        this.trainCode = trainCode;
        this.departuredate = departuredate;
    }
}