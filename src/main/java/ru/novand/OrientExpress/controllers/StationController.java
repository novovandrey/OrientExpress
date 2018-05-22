package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.OrientExpress.domain.entities.Passenger;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Ticket;
import ru.novand.OrientExpress.services.PassengerService;
import ru.novand.OrientExpress.services.StationService;
import ru.novand.OrientExpress.services.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class StationController {

    //TODO exceptionhandler

    @Autowired
    private StationService stationService;

    @RequestMapping(value = "/addstation", method = RequestMethod.GET)
    @ResponseBody
    public String addStation(@RequestParam("stationname") String stationname,
                               HttpServletRequest request, HttpServletResponse response) {

        //TODO add exceptopn handler

        Station station = stationService.addStation(stationname);

        String stationResult = "Success! Station added to list";
        return stationResult;
    }

}
