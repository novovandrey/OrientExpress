package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.impl.TrainServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrainController {

    private final TrainServiceImpl trainService;

    public TrainController(TrainServiceImpl trainService) {
        this.trainService = trainService;
    }

    @GetMapping(Routes.addtrain)
    @ResponseBody
    public String addtrain( @RequestParam("trainname") String trainname,@RequestParam("traincode") String traincode,@RequestParam("seatsnumber") String trainseats,HttpServletRequest request) {

        int seatsnumber = Integer.valueOf(trainseats);
        //Train train = trainService.addTrain(trainname, traincode, seatsnumber);
        String trainResult = "Success! Train added to list";

        return trainResult;
    }

    @GetMapping(Routes.trains)
    public ModelAndView showAllTrains(HttpServletRequest requeste){

        ModelAndView mv = new ModelAndView("/trains");
        List<Train> trains =  trainService.getAllTrains();
        mv.addObject("trains", trains);
        return mv;

    }

}
