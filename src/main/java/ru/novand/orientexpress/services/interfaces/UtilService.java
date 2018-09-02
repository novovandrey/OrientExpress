package ru.novand.orientexpress.services.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface UtilService {

    public static final DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String convertDateTimeToDate_ddMMyyyy(String departuredate) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime formatDateTime = LocalDateTime.parse(departuredate, formatter);
        return formatDateTime.format(ddMMyyyyformatter);
    }

    public static List<String> convertISODateTimeToDateAndTime(String departuredate) {

        List<String> dateTimeStr = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDate depdateFormatDate = LocalDate.parse(departuredate, formatter);

        LocalDateTime depdateFormatTime = LocalDateTime.parse(departuredate, formatter);

        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateTimeStr.add(depdateFormatDate.format(formatter));

        formatter = DateTimeFormatter.ofPattern("HH:mm");
        dateTimeStr.add(depdateFormatTime.format(formatter));

        return dateTimeStr;
    }
}
