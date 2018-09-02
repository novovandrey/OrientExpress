package ru.novand.orientexpress.domain.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
        return new ToStringBuilder(this)
                .append("id", id)
                .append("country_en", country_en)
                .append("region_en", region_en)
                .append("city_en", city_en)
                .append("country", country)
                .append("region", region)
                .append("city", city)
                .append("lat", lat)
                .append("lng", lng)
                .append("population", population)
                .toString();
    }
}
