package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.novand.OrientExpress.domain.DAO.interfaces.PassengerDAO;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.services.PassengerService;
import ru.novand.OrientExpress.services.ScheduleService;
import ru.novand.OrientExpress.services.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    //exceptionhandler

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;

    @RequestMapping(value = "/buyTicket", method= RequestMethod.GET)
    public ModelAndView buyTicket(@RequestParam String traincode, @RequestParam String departuredate,@RequestParam String departurestation, @RequestParam String arrivalstation,HttpServletRequest request, HttpServletResponse response) {

        System.out.println("TicketController BuyTicket is called");

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDate depdateFormatDate = LocalDate.parse(departuredate,formatter );

        LocalDateTime depdateFormatTime = LocalDateTime.parse(departuredate,formatter );

        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String depdateFormatDateStr = depdateFormatDate.format(formatter);

        formatter = DateTimeFormatter.ofPattern("HH:mm");
        String depdateFormatTimeStr = depdateFormatTime.format(formatter); // "1986-04-08 12:30"

        ModelAndView mv = new ModelAndView("/ticketprocess");
        mv.addObject("departuredate", depdateFormatDateStr );
        mv.addObject("departuretime", depdateFormatTimeStr );
        mv.addObject("departurestation", departurestation);
        mv.addObject("arrivalstation", arrivalstation);
        mv.addObject("traincode", traincode);
        return mv;
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    @RequestMapping(value = "/payTicket", method= RequestMethod.GET)
    @ResponseBody
        public ModelAndView payTicket(@RequestParam String FamilyName, @RequestParam String FirstName, @RequestParam String BirthDate, @RequestParam String SeatNumber
            ,@RequestParam String traincode,@RequestParam String fromSt, @RequestParam String toSt,@RequestParam String depdate ,@RequestParam String deptime
            ,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        // if we here then we need to booking a number and store order in the db

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDateFormat = LocalDate.parse(BirthDate, formatter);

        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate depdateFormat = LocalDate.parse(depdate, formatter);

        boolean checkTickets = true;
        boolean checkPassengers = true;
        boolean checkTime = true;
        boolean success = false;

        if (ticketService.checkVacantPlaces(Integer.parseInt(traincode),convertToDateViaInstant(depdateFormat))) {
            //if train has no available seats
            checkTickets = false;
        }
        if (ticketService.isPassengerAlreadyRegistered(Integer.parseInt(traincode),convertToDateViaInstant(depdateFormat), new Passenger(FirstName, FamilyName, convertToDateViaInstant(BirthDateFormat)))) {
            checkPassengers = false;
        }

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(10);
        String depdatetime = depdate + " " + deptime;
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime depdatetimeFormat = LocalDateTime.parse(depdatetime, formatter);
        if ( currentTime.isAfter(depdatetimeFormat)) {
            checkTime = false;
        }

        if(checkTickets&&checkPassengers&&checkTime)
        {
            Passenger passenger = passengerService.createPasseneger(FirstName, FamilyName, convertToDateViaInstant(BirthDateFormat));
            Ticket result = ticketService.saveTicket(traincode, fromSt, toSt, convertToDateViaInstant(depdateFormat), passenger);
            success = true;
        }

        System.out.println("payTicket is called" + success);

        String msg="Somebody went wrong!";
        String classIdent;
        if(success){
            classIdent= "alert-success";
            msg = "Congratulate your bought a ticket";
        }
        else {
            classIdent= "alert-danger";
            if (!checkTickets)
                msg = "Unfortunately, there is no available seats on the train. Ticket is not purchased";

            if (!checkPassengers)
                msg = "Unfortunately, there is has the same passenger on the train. Ticket is not purchased";

            if (!checkTime)
                msg = "Unfortunately, less than 10 minutes left before the departure of the train. Ticket is not purchased";

        }

        ModelAndView mv = new ModelAndView("/ticketIsPurchased");

        mv.addObject("ticketResult", success);
        mv.addObject("classIdent", classIdent);
        mv.addObject("msg", msg);

        return mv;

    }

}
