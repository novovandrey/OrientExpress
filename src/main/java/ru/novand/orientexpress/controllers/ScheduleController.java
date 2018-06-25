package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.services.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ScheduleController {

    private DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/schedule", method= RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllStations() {

        System.out.println("scheduleController GetAllStations is called");

        ModelAndView mv = new ModelAndView("/schedule/schedule");

        String curDate = LocalDate.now().format(ddMMyyyyformatter);

        List<Station> stations = scheduleService.GetAllStations();
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", curDate);
        return mv;
    }

    @RequestMapping(value = "/findSchedule", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView findSchedule(@RequestParam String fromSt, @RequestParam String toSt, @RequestParam String departuredate,
                                 HttpServletRequest request, HttpServletResponse response) {
        //TODO modelattribute
        //TODO @valid add hibernate valid
        System.out.println("scheduleController getSchedule is called");
        String departuretime = "10:00:00";
        List<ScheduleDTO> result = scheduleService.getSchedule(fromSt,toSt,departuredate);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/schedule/schedule_data");
        mv.addObject("results", result);

        return mv;
    }

    @RequestMapping(value = "/scheduleDetail", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView scheduleDetail(@RequestParam String traincode, @RequestParam String departuredate,@RequestParam String fromSt,@RequestParam String toSt,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("scheduleController scheduleDetail is called");
        //todo
        ModelAndView mv = new ModelAndView();

        List<ScheduleDTO> tariff = scheduleService.getTrainTariff(traincode,departuredate);

        List<ScheduleDTO> tariffOrdered = scheduleService.getTariffOrdered(tariff,fromSt,toSt);

        BigDecimal tariffvalue = scheduleService.getTariff(tariffOrdered);

        List<String> citylist = scheduleService.getCityList(tariffOrdered);

        mv.addObject("tariffvalue", tariffvalue);
        mv.addObject("tariffOrdered", tariffOrdered);
        mv.addObject("routelist", citylist);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        LocalDateTime formatDateTime = LocalDateTime.parse(departuredate, formatter);

        mv.setViewName("/schedule/scheduleDetail");
        mv.addObject("departureDate", formatDateTime);
        mv.addObject("trainCode", traincode);
        mv.addObject("fromSt", fromSt);
        mv.addObject("toSt", toSt);

        return mv;
    }

    @RequestMapping(value = "/stationschedule", method= RequestMethod.GET)
    public ModelAndView initStationSchedule() {

        System.out.println("scheduleController initStationSchedule is called");

        ModelAndView mv = new ModelAndView("/stationschedule");

        List<Station> stations = scheduleService.GetAllStations();
        String curDate = LocalDate.now().format(ddMMyyyyformatter);
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", curDate);
        return mv;
    }

    @RequestMapping(value = "/stationscheduleData", method= RequestMethod.GET)
    public ModelAndView getStationscheduleData(@RequestParam String fromSt, @RequestParam String arrivaldate,
                                               HttpServletRequest request, HttpServletResponse response) {
        System.out.println("scheduleController getScheduleByStation is called");
        List<ScheduleDTO> result = scheduleService.getScheduleByStation(fromSt,arrivaldate);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scheduleByStationData");
        mv.addObject("results", result);

        return mv;
    }

}

