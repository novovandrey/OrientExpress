package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.services.impl.StationServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StationController {

    //TODO exceptionhandler

    private final StationServiceImpl stationService;

    public StationController(StationServiceImpl stationService) {
        this.stationService = stationService;
    }

    @GetMapping(Routes.addstation)
    @ResponseBody
    public String addStation(@RequestParam("stationname") String stationname,
                               HttpServletRequest request, HttpServletResponse response) {

        //TODO add exceptopn handler

        Station station = stationService.addStation(stationname);

        String stationResult = "Success! Station added to list";
        return stationResult;
    }

}
