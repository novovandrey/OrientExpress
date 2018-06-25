package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.services.MapViewService;
import ru.novand.orientexpress.services.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class MapViewController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MapViewService mapViewService;

    @GetMapping("/getmarkers")
    public List<MapPointDTO> getMarkers(@RequestParam String traincode, @RequestParam String departuredate, @RequestParam String fromSt, @RequestParam String toSt, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("MapViewController getmarkers is called");

        List<ScheduleDTO> tariff = scheduleService.getTrainTariff(traincode, departuredate);

        List<ScheduleDTO> tariffOrdered = scheduleService.getTariffOrdered(tariff,fromSt,toSt);

        List<String> citylist = scheduleService.getCityList(tariffOrdered);

        List<MapPointDTO> mapPointDTOS = mapViewService.getJsonMarkers(citylist);

        return mapPointDTOS;
    }

//    @RequestMapping("/getmarkers")
//    public List<MapPointDTO> getmarkers(@RequestParam(value="name", defaultValue="World") String name) {
//
//        String[] cities=null;
//        List<MapPointDTO> mapPointDTOS = mapViewService.GetJsonMarkers(cities);
//        return mapPointDTOS;
//    }
}
