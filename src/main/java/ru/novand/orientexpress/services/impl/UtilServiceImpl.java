package ru.novand.orientexpress.services.impl;

import org.springframework.stereotype.Service;
import ru.novand.orientexpress.services.interfaces.UtilService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilServiceImpl implements UtilService {

    private static final DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String convertDateTimeToDate_ddMMyyyy(String date)  {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime formatDateTime = LocalDateTime.parse(date, formatter);
        return formatDateTime.format(ddMMyyyyformatter);
    }

    public static List<String> convertISODateTimeToDateAndTime(String date)  {

        List<String> dateTimeStr = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDate depdateFormatDate = LocalDate.parse(date,formatter );

        LocalDateTime depdateFormatTime = LocalDateTime.parse(date,formatter );

        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateTimeStr.add(depdateFormatDate.format(formatter));

        formatter = DateTimeFormatter.ofPattern("HH:mm");
        dateTimeStr.add(depdateFormatTime.format(formatter));

        return dateTimeStr;
    }
}
