package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.impl.PassengerServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PassengerController {

    public PassengerController(PassengerServiceImpl passengerService) {
        this.passengerService = passengerService;
    }

    private final PassengerServiceImpl passengerService;

    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @GetMapping(Routes.passengerList)
    public ModelAndView initTrains() {

        System.out.println("");
        logger.debug("PassengerController initTrains is called");

        ModelAndView mv = new ModelAndView("/passengerList");

        List<Train> trains =  passengerService.getAllTrains();
        LocalDate curDate = LocalDate.now();

        mv.addObject("trains", trains);
        mv.addObject("curDate", curDate);
        return mv;
    }


    @GetMapping(Routes.getAllPassengers)
    @ResponseBody
    public ModelAndView getAllPassengers(@RequestParam String traincode, @RequestParam String arrivaldate, HttpServletRequest request, HttpServletResponse response) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate arrivaldateFormat = LocalDate.parse(arrivaldate, formatter);

        List<Passenger> passengerlist = passengerService.getAllPassengers(traincode, arrivaldateFormat.atStartOfDay().toInstant(ZoneOffset.UTC));

        ModelAndView mv = new ModelAndView("/passengers_resp");
        mv.addObject("passengerlist", passengerlist);

        return mv;

    }
}


