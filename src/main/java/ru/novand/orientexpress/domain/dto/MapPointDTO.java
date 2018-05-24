package ru.novand.orientexpress.domain.dto;

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


}
