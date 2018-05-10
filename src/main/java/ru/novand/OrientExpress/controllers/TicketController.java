package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;

    @RequestMapping(value = "/buyTicket", method= RequestMethod.GET)
    public ModelAndView buyTicket(@RequestParam String traincode, @RequestParam String departuredate,@RequestParam String departurestation, @RequestParam String arrivalstation,HttpServletRequest request, HttpServletResponse response) {

        System.out.println("TicketController BuyTicket is called");

        ModelAndView mv = new ModelAndView("/ticketprocess");
        mv.addObject("departuredate", departuredate);
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
            ,@RequestParam String traincode,@RequestParam String fromSt, @RequestParam String toSt,@RequestParam String depdate
            ,HttpServletRequest request, HttpServletResponse response) {
        // if we here then we need to booking a number and store order in the db


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDateFormat = LocalDate.parse(BirthDate, formatter);

        LocalDate depdateFormat = LocalDate.parse(depdate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        boolean checkTickets = true;
        boolean checkPassengers = true;
        boolean checkTime = true;
        boolean success = false;

        if (ticketService.checkVacantPlaces(Integer.parseInt(traincode),depdateFormat)) {
            //if train has no available seats
            checkTickets = false;
        }
        if (ticketService.isPassengerAlreadyRegistered(Integer.parseInt(traincode),depdateFormat, new Passenger(FirstName, FamilyName, convertToDateViaInstant(BirthDateFormat)))) {
            checkPassengers = false;
        }

        LocalTime depdateTimeFormat = LocalTime.parse(depdate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalTime currentTime = LocalTime.now().plusMinutes(10);

        if ( currentTime.isAfter(depdateTimeFormat)) {
            checkTime = false;
        }

        if(checkTickets&&checkPassengers&&checkTime)
        {
            Passenger passenger = passengerService.createPasseneger(FirstName, FamilyName, convertToDateViaInstant(BirthDateFormat));
            //Ticket result = ticketService.saveTicket(traincode, fromSt, toSt, depdate, passenger);
            success = true;
        }

        System.out.println("payTicket is called" + success);

        //ModelAndView mv = new ModelAndView("/paygate.html");

        ModelAndView mv = new ModelAndView("/ticketprocess");

        mv.addObject("ticketResult", success);
        return mv;

    }

}
