package ru.novand.orientexpress.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PassengerForm {

    @Size(max = 255, min = 1, message="Family name is empty")
    @NotNull(message="Family name is empty")

    private String familyname;
    @Size(max = 255, min = 1, message="Firstname name is empty")
    @NotNull(message="Firstname name is empty")
    private String firstname;

    @Size(max = 255, min = 1, message="Birthdate name is empty")
    @NotNull(message="Birthdate name is empty")
    private String birthdate;

    private String traincode;
    private String fromSt;
    private String toSt;
    private String depdate;
    private String deptime;

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode;
    }

    public String getFromSt() {
        return fromSt;
    }

    public void setFromSt(String fromSt) {
        this.fromSt = fromSt;
    }

    public String getToSt() {
        return toSt;
    }

    public void setToSt(String toSt) {
        this.toSt = toSt;
    }

    public String getDepdate() {
        return depdate;
    }

    public void setDepdate(String depdate) {
        this.depdate = depdate;
    }

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
