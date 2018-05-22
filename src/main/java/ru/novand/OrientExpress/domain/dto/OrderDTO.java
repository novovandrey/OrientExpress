package ru.novand.OrientExpress.domain.dto;

public class OrderDTO {

    private String FamilyName;
    private String FirstName;
    private String BirthDate;
    private String TrainCode;
    private String DepartureDate;

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getTrainCode() {
        return TrainCode;
    }

    public void setTrainCode(String trainCode) {
        TrainCode = trainCode;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public OrderDTO(String familyName, String firstName, String birthDate, String trainCode, String departureDate) {
        FamilyName = familyName;
        FirstName = firstName;
        BirthDate = birthDate;
        TrainCode = trainCode;
        DepartureDate = departureDate;
    }
}
