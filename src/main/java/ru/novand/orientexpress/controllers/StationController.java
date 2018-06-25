package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.services.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
