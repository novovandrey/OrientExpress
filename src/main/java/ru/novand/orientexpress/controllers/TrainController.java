package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.StationService;
import ru.novand.orientexpress.services.TrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrainController {

    @Autowired
    TrainService trainService;
    @Autowired
    StationService stationService;

    @RequestMapping(value = "/addtrain", method = RequestMethod.GET)
    @ResponseBody
    public String addtrain( @RequestParam("trainname") String trainname,@RequestParam("traincode") String traincode,@RequestParam("seatsnumber") String trainseats,HttpServletRequest request) {

        int seatsnumber = Integer.valueOf(trainseats);
        Train train = trainService.addTrain(trainname, traincode, seatsnumber);
        String trainResult = "Success! Train added to list";

        return trainResult;
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public ModelAndView showAllTrains(HttpServletRequest requeste){

        ModelAndView mv = new ModelAndView("/trains");
        List<Train> trains =  trainService.getAllTrains();
        mv.addObject("trains", trains);
        return mv;

    }

//    @RequestMapping(value = "/trains", method= RequestMethod.GET)
//    public ModelAndView InitTrains() {
//
//        System.out.println("TrainController InitTrains is called");
//
//        ModelAndView mv = new ModelAndView("/trains");
//
//        List<Train> trains =  trainService.getAllTrains();
//        java.sql.Date myDate = new java.sql.Date(System.currentTimeMillis());
//
//        mv.addObject("trains", trains);
//        mv.addObject("curDate", myDate);
//        return mv;
//    }

}
