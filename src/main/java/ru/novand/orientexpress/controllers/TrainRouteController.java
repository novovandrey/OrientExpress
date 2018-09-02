package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.novand.orientexpress.domain.dto.TrainRouteDTO;
import ru.novand.orientexpress.domain.entities.Station;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.domain.entities.TrainRoute;
import ru.novand.orientexpress.services.impl.TrainRouteServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainRouteController {

    private final TrainRouteServiceImpl trainRouteService;

    public TrainRouteController(TrainRouteServiceImpl trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @PostMapping(Routes.initTrainroute)
    public ModelAndView initTrainroute(final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("trainroutenew_row");
        List<Station> stations = trainRouteService.getAllStations();
        List<Train> trainsWRoutes = trainRouteService.getAllTrainsInTrainRoute();

        if(trainsWRoutes.size()==0) mv.addObject("error", "error");

        mv.addObject("stations", stations);
        mv.addObject("trains", trainsWRoutes);

        return mv;

    }

    @GetMapping(Routes.trainRouteList)
    public String showAllTrainRouteList(Model model) {

        model.addAttribute("trainRouteList", trainRouteService.getAllTrainRoute());
        return "trainRouteList";

    }


    @PostMapping(Routes.trainrouteAdd)
    public ModelAndView addTrainroute(@RequestBody TrainRouteDTO trainRouteDTO,Model model) {
        ModelAndView mv = new ModelAndView("trainroute_row");
        List<TrainRoute> trainRouteList = new ArrayList<>();
        trainRouteList.add(trainRouteService.save(trainRouteDTO.getArrst(), trainRouteDTO.getDepst(), trainRouteDTO.getTraincode()));
        mv.addObject("trainRouteNewList", trainRouteList);
        return mv;
    }


    @PostMapping(value = "/trainroute/{id}/save")
    public String saveTrainRoute(@RequestBody TrainRouteDTO trainRouteDTO, @PathVariable("id") int id,
                                 final RedirectAttributes redirectAttributes) {

        trainRouteService.update(trainRouteDTO.getArrst(), trainRouteDTO.getDepst(), trainRouteDTO.getTraincode(),id);
        redirectAttributes.addFlashAttribute("msg", "TrainRoute is saved!");
        return "trainRouteList";

    }

    @PostMapping(value = "/trainroute/{id}/delete")
    public String deleteTrainroute(@PathVariable("id") String traincode,
                                 final RedirectAttributes redirectAttributes) {

        trainRouteService.delete(traincode);

        redirectAttributes.addFlashAttribute("msg", "trainroute is deleted!");

        return "trainRouteList";

    }

}
