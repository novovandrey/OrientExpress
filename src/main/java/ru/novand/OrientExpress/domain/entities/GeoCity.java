package ru.novand.OrientExpress.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "GEOCITY")
public class GeoCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "country_en")
    private String country_en;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "region_en")
    private String region_en;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "city_en")
    private String city_en;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "country")
    private String country;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "region")
    private String region;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "city")
    private String city;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "lat")
    private String lat;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "lng")
    private String lng;

    @NotEmpty
    @Size(min = 0, max = 255)
    @Column(name = "population")
    private int population;

    public GeoCity() {

    }

    @Override
    public String toString() {
        return "GeoCity{" +
                "idpassenger=" + id +
                ", country_en='" + country_en +
                ", city_en ='" + city_en+
                '}';
    }
}
