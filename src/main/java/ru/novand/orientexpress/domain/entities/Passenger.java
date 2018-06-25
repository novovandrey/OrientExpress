package ru.novand.orientexpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "PASSENGER")
@NamedQueries({
        @NamedQuery(name = "Passenger.find", query = "FROM Passenger where idpassenger= :passengerid"),
        @NamedQuery(name = "Passenger.findbyUserID", query = "FROM Passenger where user.user_id= :userid"),
        @NamedQuery(name = "Passenger.findbyUserName", query = "FROM Passenger where user.username= :username")
})
public class Passenger implements Serializable {

    //TODO add registrationID, registrationdate, updatorID, updatedate

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSENGER_ID")
    private int idpassenger;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "FAMILYNAME")
    private String familyname;

    @NotNull
    @Column(name = "BIRTHDATE")
    //todo instant
    private Instant birthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> ticketList;

    public Passenger() {}

    public int getIdpassenger() {
        return idpassenger;
    }

    public void setIdpassenger(int idpassenger) {
        this.idpassenger = idpassenger;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(Set<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Passenger(String firstname, String familyname, Instant birthdate) {
        this.firstname = firstname;
        this.familyname = familyname;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "idpassenger=" + idpassenger +
                ", firstname='" + firstname +
                ", familyname='" + familyname +
                ", birthdate='" + birthdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(firstname, passenger.firstname) &&
                Objects.equals(familyname, passenger.familyname) &&
                Objects.equals(birthdate, passenger.birthdate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname, familyname, birthdate);
    }
}
