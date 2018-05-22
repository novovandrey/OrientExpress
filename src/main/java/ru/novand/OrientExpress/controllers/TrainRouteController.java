package ru.novand.OrientExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.novand.OrientExpress.domain.dto.TrainRouteDTO;
import ru.novand.OrientExpress.domain.dto.TrainScheduleDTO;
import ru.novand.OrientExpress.domain.entities.Station;
import ru.novand.OrientExpress.domain.entities.Train;
import ru.novand.OrientExpress.domain.entities.TrainRoute;
import ru.novand.OrientExpress.services.ScheduleService;
import ru.novand.OrientExpress.services.TrainRouteService;
import ru.novand.OrientExpress.services.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainRouteController {

    @Autowired
    TrainRouteService trainRouteService;
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TrainService trainService;

//    @RequestMapping(value = "/addTrainRoute", method = RequestMethod.GET)
//    @ResponseBody
//    public String addtrain( @RequestParam("trainname") String trainname,@RequestParam("traincode") String traincode,@RequestParam("seatsnumber") String trainseats,HttpServletRequest request) {
//
//        int seatsnumber = Integer.valueOf(trainseats);
//        Train train = trainService.addTrain(trainname, traincode, seatsnumber);
//        String trainResult = "Success! Train added to list";
//
//        return trainResult;
//    }

//    @RequestMapping(value = "/trList", method = RequestMethod.GET)
//    public String index(Model model) {
//        return "redirect:/trainRouteList";
//    }

    @RequestMapping(value = "/trainRouteList", method = RequestMethod.GET)
    public String showAllTrainRouteList(Model model) {

        model.addAttribute("trainRouteList", trainRouteService.getAllTrainRoute());
        return "trainRouteList";

    }


    @RequestMapping(value = "/schedule/{id}/delete", method = RequestMethod.POST)
    public String deleteSchedule(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {

        scheduleService.delete(id);

        redirectAttributes.addFlashAttribute("msg", "schedule is deleted!");

        return "trainRouteList";

    }


    @RequestMapping(value = "/schedule/{id}/save", method = RequestMethod.POST)
    public String saveSchedule(@RequestBody TrainScheduleDTO trainScheduleDTO, @PathVariable("id") int id,
                               final RedirectAttributes redirectAttributes) {

        scheduleService.update(trainScheduleDTO.getFromst(), trainScheduleDTO.getTost(), trainScheduleDTO.getInterval(),id);
        redirectAttributes.addFlashAttribute("msg", "schedule is saved!");
        return "trainRouteList";

    }

    @RequestMapping(value = "/trainroute/{id}/save", method = RequestMethod.POST)
    public String saveTrainRoute(@RequestBody TrainRouteDTO trainRouteDTO, @PathVariable("id") int id,
                                 final RedirectAttributes redirectAttributes) {

        trainRouteService.update(trainRouteDTO.getArrst(), trainRouteDTO.getDepst(), trainRouteDTO.getTraincode(),id);
        redirectAttributes.addFlashAttribute("msg", "TrainRoute is saved!");
        return "trainRouteList";

    }

    @RequestMapping(value = "/trainroute/{id}/delete", method = RequestMethod.POST)
    public String deleteTrainroute(@PathVariable("id") String traincode,
                                 final RedirectAttributes redirectAttributes) {

        trainRouteService.delete(traincode);

        redirectAttributes.addFlashAttribute("msg", "trainroute is deleted!");

        return "trainRouteList";

    }

    @RequestMapping(value = "/initTrainroute", method = RequestMethod.POST)
    public ModelAndView initTrainroute(final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("trainroute_row");
        List<Station> stations = scheduleService.GetAllStations();
        List<Train> trains = trainService.getAllTrains();
        List<Train> trainsWRoutes = trainRouteService.getAllTrainsInTrainRoute(trains);

        if(trainsWRoutes.size()==0) mv.addObject("error", "error");

        mv.addObject("stations", stations);
        mv.addObject("trains", trainsWRoutes);

        return mv;

    }

    @RequestMapping(value = "/trainroute/add", method = RequestMethod.POST)
    public ModelAndView addTrainroute(@RequestBody TrainRouteDTO trainRouteDTO,Model model) {
        ModelAndView mv = new ModelAndView("trainroute_row");
        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRouteService.save(trainRouteDTO.getArrst(), trainRouteDTO.getDepst(), trainRouteDTO.getTraincode()));
        mv.addObject("trainRouteList", trainRouteList);
        return mv;

    }

}
