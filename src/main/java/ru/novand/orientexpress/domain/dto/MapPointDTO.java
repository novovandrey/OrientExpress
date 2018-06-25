package ru.novand.orientexpress.domain.dto;

import java.util.Objects;

public class MapPointDTO {

    private double sortfield;
    private String name;
    private String lat;
    private String lng;

    public MapPointDTO(String name, String lat, String lng) {
        sortfield = Double.parseDouble(lat);
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }


    public double getSortfield() {
        return sortfield;
    }
    public void setSortfield(double sortfield) {
        this.sortfield = sortfield;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLat() {
        return lat;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapPointDTO that = (MapPointDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lng, that.lng);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lat, lng);
    }
}
