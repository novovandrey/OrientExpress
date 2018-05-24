package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.TrainService;

import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/passengerList", method= RequestMethod.GET)
    public ModelAndView initTrains() {

        System.out.println("PassengerController InitTrains is called");

        ModelAndView mv = new ModelAndView("/passengerList");

        List<Train> trains =  trainService.getAllTrains();
        java.sql.Date myDate = new java.sql.Date(System.currentTimeMillis());

        mv.addObject("trains", trains);
        mv.addObject("curDate", myDate);
        return mv;
    }
}


