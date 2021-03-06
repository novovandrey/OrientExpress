package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@NamedQuery(name = "User.findByName", query = "FROM User where username= :username")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int idUser;

    @NotEmpty
    @Size(min = 5, max = 20)
    @Column(name = "USERNAME")
    private String username;

    @NotEmpty
    @Size(min=5, max=20)
    @Column(name="PASSWORD")
    private String password;

    @NotEmpty
    @Size(min=0, max=100)
    @Column(name="EMAIL")
    private String email;

    @Column(name="ENABLED")
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengerlist = new ArrayList<>();

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Passenger> getPassengerlist() {
        return passengerlist;
    }

    public void setPassengerlist(List<Passenger> passengerlist) {
        this.passengerlist = passengerlist;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
