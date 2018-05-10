package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "PASSENGER")
public class Passenger implements Serializable {

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

    @NotEmpty
    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> ticketList;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(Set<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Passenger() {
        System.out.println("Passenger");
    }

    public Passenger(String firstname, String familyname, Date birthdate) {
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
}
