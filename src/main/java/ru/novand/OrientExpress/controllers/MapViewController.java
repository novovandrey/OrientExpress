package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.novand.OrientExpress.domain.dto.MapPointDTO;
import ru.novand.OrientExpress.domain.dto.ScheduleDTO;
import ru.novand.OrientExpress.services.MapViewService;
import ru.novand.OrientExpress.services.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MapViewController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MapViewService mapViewService;

    @RequestMapping(value = "/getmarkers", method= RequestMethod.GET)
    public List<MapPointDTO> getMarkers(@RequestParam String traincode, @RequestParam String departuredate, @RequestParam String fromSt, @RequestParam String toSt, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("MapViewController getmarkers is called");

        List<ScheduleDTO> tariff = scheduleService.getTrainTariff(traincode,departuredate);

        String prevVertex = null;
        String curVertex = fromSt;
        boolean flag=true;

        List<String> citylist = new ArrayList<>();

        while (flag)
        {
            for(ScheduleDTO aSiteId: tariff) {
                if (curVertex.equals(aSiteId.getDepstationname()))
                {
                    citylist.add(curVertex);
                    prevVertex = curVertex;
                    curVertex = aSiteId.getArrstationname();

                    if (curVertex.equals(toSt))
                    {
                        citylist.add(toSt);
                        flag = false;
                        break;
                    }
                    continue;
                }
            }
            if (curVertex.equals(toSt)) flag = false;
        }

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
