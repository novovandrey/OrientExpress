package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.dto.ScheduleJSON;
import ru.novand.orientexpress.domain.dto.StationDTO;
import ru.novand.orientexpress.domain.dto.TrainScheduleDTO;
import ru.novand.orientexpress.domain.entities.Schedule;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.mappers.StationMapper;
import ru.novand.orientexpress.model.ScheduleForm;
import ru.novand.orientexpress.services.impl.ScheduleServiceImpl;
import ru.novand.orientexpress.utils.MessageSender;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ScheduleController {

    private DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final ScheduleServiceImpl scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    private StationMapper stationMapper;
    private String stationValue_Service;

    @GetMapping(Routes.schedule)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllStations() {

        logger.debug("scheduleController getAllStations is called");

        ModelAndView mv = new ModelAndView("/schedule/schedule");

        String curDate = LocalDate.now().format(ddMMyyyyformatter);
        List<Station> stations = scheduleService.getAllStations();
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", curDate);
        return mv;
    }

    @PostMapping(Routes.findSchedule)
    @ResponseBody
    public ModelAndView findSchedule(@RequestParam String fromSt, @RequestParam String toSt, @RequestParam String departuredate,
                                     HttpServletRequest request, HttpServletResponse response) {
        //TODO modelattribute
        //TODO @valid add hibernate valid
        ModelAndView mv = new ModelAndView();

        logger.debug("scheduleController getSchedule is called");
        String departuretime = "00:00:00";
        List<ScheduleDTO> scheduleDTOList = scheduleService.getSchedule(fromSt,toSt,departuredate);

        mv.setViewName("/schedule/schedule_data");
        mv.addObject("results", scheduleDTOList);

        return mv;
    }

    @GetMapping(Routes.scheduleDetail)
    @ResponseBody
    public ModelAndView scheduleDetail(@RequestParam String traincode, @RequestParam String departuredate,@RequestParam String fromSt,@RequestParam String toSt,HttpServletRequest request, HttpServletResponse response) {
        logger.debug("scheduleController scheduleDetail is called");
        ModelAndView mv = new ModelAndView();

        List<ScheduleDTO> tariff = scheduleService.getTrainTariff(traincode,departuredate);

        List<ScheduleDTO> tariffOrdered = scheduleService.getTariffOrdered(tariff,fromSt,toSt);

        BigDecimal tariffvalue = scheduleService.getTariff(tariffOrdered);

        List<String> citylist = scheduleService.getCityList(tariffOrdered);

        mv.addObject("tariffvalue", tariffvalue);
        mv.addObject("tariffOrdered", tariffOrdered);
        mv.addObject("routelist", citylist);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime formatDateTime = LocalDateTime.parse(departuredate, formatter);

        mv.setViewName("/schedule/scheduleDetail");
        mv.addObject("departureDate", formatDateTime);
        mv.addObject("trainCode", traincode);
        mv.addObject("fromSt", fromSt);
        mv.addObject("toSt", toSt);

        return mv;
    }

    @GetMapping(Routes.stationschedule)
    public ModelAndView initStationSchedule() {

        logger.debug("scheduleController initStationSchedule is called");
        ModelAndView mv = new ModelAndView("/stationschedule");

        List<Station> stations = scheduleService.getAllStations();
        String curDate = LocalDate.now().format(ddMMyyyyformatter);
        mv.addObject("stationsResult", stations);
        mv.addObject("curDate", curDate);
        return mv;
    }

    @GetMapping("/allstation")
    @ResponseBody
    public List<StationDTO> getAllStation() {
        logger.debug("scheduleController webservice request. allstation is called");

        List<Station> stations = scheduleService.getAllStations();

        List<StationDTO> stationsDTO = new ArrayList<>();

        for ( Station station : stations ) {
            stationsDTO.add( stationMapper.INSTANCE.stationToStationDTO( station ) );
        }

        return stationsDTO;
    }

    @RequestMapping("/scheduledataWS")
    @ResponseBody
    public List<ScheduleJSON> getScheduledataWS(@RequestParam String station ) {

        logger.debug("scheduleController getScheduledataWS is called");
        if(station.isEmpty()) {
            stationValue_Service = "Moscow";
        }
        else{
            stationValue_Service = station;
        }
        LocalDate today = LocalDate.now();
        String arrivaldate = (today).format(ddMMyyyyformatter);

        List<ScheduleDTO> result = scheduleService.getScheduleByStation(stationValue_Service,arrivaldate);
        List<ScheduleJSON> jsonResult = new ArrayList<>();
        for (ScheduleDTO scheduleDTO: result) {
            jsonResult.add(new ScheduleJSON(scheduleDTO.getTraincode(),scheduleDTO.getDeparturedate(),scheduleDTO.getDepstationname(),scheduleDTO.getArrivaldate(),scheduleDTO.getArrstationname(),scheduleDTO.getDoubleIterator()));
        }
        logger.debug("scheduleController schedule dto list: "+jsonResult);
        return jsonResult;
    }

    @GetMapping(Routes.stationscheduleData)
    public ModelAndView getStationscheduleData(@RequestParam String fromSt, @RequestParam String arrivaldate,
                                               HttpServletRequest request, HttpServletResponse response) {
        logger.debug("scheduleController getScheduleByStation is called");
        List<ScheduleDTO> result = scheduleService.getScheduleByStation(fromSt,arrivaldate);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scheduleByStationData");
        mv.addObject("results", result);

        return mv;
    }



    @PostMapping(value = "/schedule/{id}/delete")
    public String deleteSchedule(@PathVariable("id") int id,
                                 final RedirectAttributes redirectAttributes) {

        scheduleService.delete(id);

        redirectAttributes.addFlashAttribute("msg", "schedule is deleted!");

        return "trainRouteList";

    }


    @PostMapping(value = "/schedule/{id}/save")
    public String saveSchedule(@RequestBody TrainScheduleDTO trainScheduleDTO, @PathVariable("id") int id,
                               final RedirectAttributes redirectAttributes) {

        scheduleService.update(trainScheduleDTO.getFromst(), trainScheduleDTO.getTost(), trainScheduleDTO.getInterval(),id);
        redirectAttributes.addFlashAttribute("msg", "schedule is saved!");
        return "trainRouteList";

    }

    @GetMapping(value = "/schedule/add")
    public ModelAndView addSchedule(@RequestParam String arrst, @RequestParam String depst, @RequestParam String interval, @RequestParam String traincode, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("schedule_row");

        List<Schedule> scheduleNewList = new ArrayList<>();
        scheduleNewList.add(scheduleService.save(arrst,depst,interval,traincode));

        redirectAttributes.addFlashAttribute("msg", "schedule is added!");
        mv.addObject("scheduleNewList", scheduleNewList);
        MessageSender.sendMessage(1);
        return mv;

    }

}

