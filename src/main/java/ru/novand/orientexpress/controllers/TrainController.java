package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.dto.UserDTO;
import ru.novand.orientexpress.domain.entities.Train;
import ru.novand.orientexpress.services.impl.TrainServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class TrainController {

    private final TrainServiceImpl trainService;

    public TrainController(TrainServiceImpl trainService) {
        this.trainService = trainService;
    }

    @GetMapping(Routes.addtrain)
    @ResponseBody
    public String addtrain(@ModelAttribute("user") @Valid UserDTO user, BindingResult result,
                            Model model, @RequestParam("trainname") String trainname, @RequestParam("traincode") String traincode, @RequestParam("seatsnumber") String trainseats) {

        if (result.hasErrors()) {
            return "addstation_emp";
        }

        int seatsnumber = Integer.valueOf(trainseats);
        Train train1 = trainService.addTrain(trainname, traincode, seatsnumber);
        String trainResult = "Success! Train added to list";

        return trainResult;
    }

    @GetMapping("/addtrain1")
    public String addtrain(@ModelAttribute("user") @Valid UserDTO user, BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            return "addstation_emp";
        }

        String trainResult = "Success! Train added to list";

        return trainResult;
    }

    @GetMapping("/addstation_emp")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("user", new UserDTO());
        return "addstation_emp";
    }

    @GetMapping(Routes.trains)
    public ModelAndView showAllTrains(HttpServletRequest requeste){

        ModelAndView mv = new ModelAndView("/trains");
        List<Train> trains =  trainService.getAllTrains();
        mv.addObject("trains", trains);
        return mv;

    }

}
