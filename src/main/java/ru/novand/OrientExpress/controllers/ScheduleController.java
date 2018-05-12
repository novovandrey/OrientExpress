package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.OrientExpress.domain.dto.ScheduleDto;
import ru.novand.OrientExpress.domain.entities.Schedule;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.TrainRoute;
import ru.novand.OrientExpress.services.MapViewService;
import ru.novand.OrientExpress.services.ScheduleService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/schedule", method= RequestMethod.GET)
    @RolesAllowed(value={"ROLE_SUPER_USER", "ROLE_ADMIN"})
    public ModelAndView GetAllStations() {

        System.out.println("scheduleController GetAllStations is called");

        ModelAndView mv = new ModelAndView("/schedule/schedule");

        List<Station> stations = scheduleService.GetAllStations();
        java.sql.Date myDate = new java.sql.Date(System.currentTimeMillis());
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", myDate);
        return mv;
    }

    @RequestMapping(value = "/findSchedule", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView findSchedule(@RequestParam String fromSt, @RequestParam String toSt, @RequestParam String departuredate,
                                 HttpServletRequest request, HttpServletResponse response) {
        System.out.println("scheduleController GetSchedule is called");
        String departuretime = "10:00:00";
        List<ScheduleDto> result = scheduleService.GetSchedule(fromSt,toSt,departuredate,departuretime);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/schedule/request-data");
        mv.addObject("results", result);

        return mv;
    }

    @RequestMapping(value = "/scheduleDetail", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView scheduleDetail(@RequestParam String traincode, @RequestParam String departuredate,@RequestParam String fromSt,@RequestParam String toSt,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("scheduleController scheduleDetail is called");

        ModelAndView mv = new ModelAndView();

        List<ScheduleDto> tariff = scheduleService.GetTrainTariff(traincode,departuredate);

        List<ScheduleDto> tariffOrdered = new ArrayList<>();

        String prevVertex = null;
        String curVertex = fromSt;
        BigDecimal tariffvalue = BigDecimal.valueOf(0);
        boolean flag=true;

        List<String> citylist = new ArrayList<>();

        while (flag)
        {
            for(ScheduleDto aSiteId: tariff) {
                if (curVertex.equals(aSiteId.getDepstationname()))
                {
                    citylist.add(curVertex);
                    prevVertex = curVertex;
                    curVertex = aSiteId.getArrstationname();

                    tariffOrdered.add(new ScheduleDto("",prevVertex,curVertex,0));

                    tariffvalue = aSiteId.getDoubleIterator().add(tariffvalue);

                    if (curVertex.equals(toSt))
                    {
                        flag = false;
                        break;
                    }
                    continue;
                }
            }
            if (curVertex.equals(toSt)) flag = false;
        }

        mv.addObject("tariffvalue", tariffvalue);
        mv.addObject("tariffOrdered", tariffOrdered);
        mv.addObject("routelist", citylist);

        List<TrainRoute> trainRouteList = scheduleService.GetTrainRoute(traincode,departuredate);
        mv.addObject("trainRouteList", trainRouteList);

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
    public ModelAndView InitStationSchedule() {

        System.out.println("scheduleController InitStationSchedule is called");

        ModelAndView mv = new ModelAndView("/stationschedule");

        List<Station> stations = scheduleService.GetAllStations();
        java.sql.Date myDate = new java.sql.Date(System.currentTimeMillis());
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", myDate);
        return mv;
    }

    @RequestMapping(value = "/stationscheduleData", method= RequestMethod.GET)
    public ModelAndView GetStationscheduleData(@RequestParam String fromSt, @RequestParam String arrivaldate,
                                              HttpServletRequest request, HttpServletResponse response) {
        System.out.println("scheduleController GetScheduleByStation is called");
        List<ScheduleDto> result = scheduleService.GetScheduleByStation(fromSt);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scheduleByStationData");
        mv.addObject("results", result);

        return mv;
    }

}

