package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.exception.RouteNotExistingException;
import ru.novand.orientexpress.services.PassengerService;
import ru.novand.orientexpress.services.ScheduleService;
import ru.novand.orientexpress.services.TicketService;
import ru.novand.orientexpress.services.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    //todo exceptionhandler

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @RequestMapping(value = "/buyTicket", method= RequestMethod.GET)
    public ModelAndView buyTicket(@RequestParam String traincode, @RequestParam String departuredate,
                                  @RequestParam String departurestation, @RequestParam String arrivalstation,
                                  HttpServletRequest request, HttpServletResponse response)  throws Exception {

        //todo switch off js and try buy ticket
        System.out.println("TicketController BuyTicket is called");

        String departureDateFormat = ticketService.convertDateTimeToDate_ddMMyyyy(departuredate);
        boolean routeListExist = scheduleService.checkSchedule(departurestation,arrivalstation,departureDateFormat);

        boolean trainExist = trainService.checkTrainExistByTrainCode(traincode);

        if (!routeListExist||!trainExist) throw new RouteNotExistingException(departurestation,arrivalstation,traincode,departuredate );

        List<String> dateTimeStr = ticketService.convertISODateTimeToDateAndTime(departuredate);

        ModelAndView mv = new ModelAndView("/ticketprocess");
        mv.addObject("departuredate", dateTimeStr.get(0) );
        mv.addObject("departuretime", dateTimeStr.get(1) );
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

    @Secured("ROLE_USER")
    @RequestMapping(value = "/payTicket", method= RequestMethod.GET)
    @ResponseBody
        public ModelAndView payTicket(@RequestParam String FamilyName, @RequestParam String FirstName,  @RequestParam String BirthDate, @RequestParam(value = "SeatNumber", required=false) String SeatNumber
            ,@RequestParam String traincode,@RequestParam String fromSt, @RequestParam String toSt,@RequestParam String depdate ,@RequestParam String deptime
            ,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        // if we here then we need to booking a number and store order in the db

        LocalDate BirthDateFormat = LocalDate.parse(BirthDate, ddMMyyyyformatter);
        LocalDate depdateFormat = LocalDate.parse(depdate, ddMMyyyyformatter);

        boolean checkTickets = true;
        boolean checkPassengers = true;
        boolean checkTime = true;
        boolean success = false;

        if (ticketService.checkVacantPlaces(Integer.parseInt(traincode),convertToDateViaInstant(depdateFormat))) {
            //if train has no available seats
            checkTickets = false;
        }
        if (ticketService.isPassengerAlreadyRegistered(Integer.parseInt(traincode),convertToDateViaInstant(depdateFormat), new Passenger(FirstName, FamilyName, BirthDateFormat))) {
            checkPassengers = false;
        }

        Instant instant = Instant.now();

        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(10);
        String depdatetime = depdate + " " + deptime;
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime depdatetimeFormat = LocalDateTime.parse(depdatetime, formatterDateTime);
        if ( currentTime.isAfter(depdatetimeFormat)) {
            checkTime = false;
        }

        if (checkTickets && checkPassengers && checkTime) {
            String username = request.getUserPrincipal().getName();
            Passenger passenger = passengerService.createPasseneger(FirstName, FamilyName, BirthDateFormat, username);
            Ticket result = ticketService.saveTicket(traincode, fromSt, toSt, convertToDateViaInstant(depdateFormat), passenger);
            success = true;
        }

        System.out.println("payTicket is called" + success);

        String msg = messageSource.getMessage("unexpected_error", null, LocaleContextHolder.getLocale());
        String classIdent;
        if (success) {
            classIdent = "alert-success";
            msg = messageSource.getMessage("success_processpay", null, LocaleContextHolder.getLocale());
        } else {
            classIdent = "alert-danger";
            if (!checkTickets)
                msg = messageSource.getMessage("no_seats", null, LocaleContextHolder.getLocale());

            if (!checkPassengers)
                msg = messageSource.getMessage("passengerAlreadyExists", null, LocaleContextHolder.getLocale());

            if (!checkTime)
                msg = messageSource.getMessage("less10min", null, LocaleContextHolder.getLocale());

        }
        ModelAndView mv = new ModelAndView("/ticketIsPurchased");

        mv.addObject("ticketResult", success);
        mv.addObject("classIdent", classIdent);
        mv.addObject("msg", msg);

        return mv;

    }

    @RequestMapping(value = "/getAllPassengers", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAllPassengers(@RequestParam String traincode,@RequestParam String arrivaldate,HttpServletRequest request, HttpServletResponse response) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate arrivaldateFormat = LocalDate.parse(arrivaldate, formatter);

        List<Passenger> passengerlist = ticketService.getAllPassengers(traincode,convertToDateViaInstant(arrivaldateFormat)) ;

        ModelAndView mv = new ModelAndView("/passengers_resp");
        mv.addObject("passengerlist", passengerlist);

        return mv;

    }

    @ExceptionHandler(RouteNotExistingException.class)
    public ModelAndView handleRouteNotExistingException(HttpServletRequest request,
                                                        Exception ex) {
        logger.error("Requested URL=" + request.getRequestURL());
        logger.error("Exception Raised=" + ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("route", "This route is not exist");
        modelAndView.addObject("url", "This route is not exist");
        modelAndView.setViewName("error");

        return modelAndView;
    }

}
