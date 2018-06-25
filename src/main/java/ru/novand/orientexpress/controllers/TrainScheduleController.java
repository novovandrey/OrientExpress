package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.novand.orientexpress.services.TrainScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TrainScheduleController {

    @Autowired
    private TrainScheduleService trainScheduleService;

    @RequestMapping(value = "/addTrainSchedule", method = RequestMethod.POST)
    @ResponseBody
    public String addTrainSchedule(@RequestParam("traincode") String traincode,@RequestParam("departuredate") String departuredate,
                               HttpServletRequest request, HttpServletResponse response) {

        //TODO add exceptopn handler

        trainScheduleService.addTrainSchedule(traincode,departuredate);

        String stationResult = "Success! Station added to list";
        return stationResult;
    }

}
