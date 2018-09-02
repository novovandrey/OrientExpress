package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.services.impl.TrainRouteServiceImpl;
import ru.novand.orientexpress.services.impl.TrainScheduleServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TrainScheduleController {

    private final TrainScheduleServiceImpl trainScheduleService;

    private final TrainRouteServiceImpl trainRouteService;

    public TrainScheduleController(TrainScheduleServiceImpl trainScheduleService, TrainRouteServiceImpl trainRouteService) {
        this.trainScheduleService = trainScheduleService;

        this.trainRouteService = trainRouteService;
    }

    @PostMapping(Routes.addTrainSchedule)
    @ResponseBody
    public String addTrainSchedule(@RequestParam("traincode") String traincode,@RequestParam("departuredate") String departuredate,
                               HttpServletRequest request, HttpServletResponse response) {

        //TODO add exceptopn handler

        trainScheduleService.addTrainSchedule(traincode,departuredate);

        String stationResult = "Success! Station added to list";
        return stationResult;
    }

    @PostMapping("/initTrainSchedule/{traincode}")
    public ModelAndView initTrainSchedule(@PathVariable("traincode") String traincode,final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("trainschedule_row");
        List<Station> stations = trainRouteService.getAllStations();

        mv.addObject("traincode", traincode);
        mv.addObject("stations", stations);

        return mv;

    }

}
