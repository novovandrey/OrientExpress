package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.novand.orientexpress.domain.dto.MapPointDTO;
import ru.novand.orientexpress.services.impl.MapViewServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MapViewController {

    private final MapViewServiceImpl mapViewService;

    private static final Logger logger = LoggerFactory.getLogger(MapViewController.class);

    public MapViewController(MapViewServiceImpl mapViewService) {
        this.mapViewService = mapViewService;
    }

    @GetMapping(Routes.getmarkers)
    public List<MapPointDTO> getMarkers(@RequestParam String traincode, @RequestParam String departuredate, @RequestParam String fromSt, @RequestParam String toSt, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("MapViewController getmarkers method was called");

        List<MapPointDTO> mapPointDTOS = mapViewService.getJsonMarkers(traincode, departuredate,fromSt,toSt);

        return mapPointDTOS;
    }

}
