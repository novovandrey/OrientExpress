package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.exception.RouteNotExistingException;
import ru.novand.orientexpress.model.PassengerForm;
import ru.novand.orientexpress.services.impl.BookServiceImpl;
import ru.novand.orientexpress.services.impl.UtilServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class TicketController {

    //todo exceptionhandler
    private final BookServiceImpl bookService;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    public TicketController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping(Routes.buyTicket)
    public ModelAndView buyTicket(@RequestParam String traincode, @RequestParam String departuredate,
                                  @RequestParam String departurestation, @RequestParam String arrivalstation,
                                  HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception {

        //todo switch off js and try buy ticket
        logger.debug("TicketController buyTicket method was called");

        String departureDateFormat = UtilServiceImpl.convertDateTimeToDate_ddMMyyyy(departuredate);
        boolean routeListExist = bookService.checkSchedule(departurestation,arrivalstation,departureDateFormat);

        boolean trainExist = bookService.checkTrainExistByTrainCode(traincode);

        if (!routeListExist||!trainExist) throw new RouteNotExistingException(departurestation,arrivalstation,traincode,departuredate );

        List<String> dateTimeStr = UtilServiceImpl.convertISODateTimeToDateAndTime(departuredate);
        model.addAttribute("passengerForm", new PassengerForm());
        ModelAndView mv = new ModelAndView("/ticketprocess");
        mv.addObject("departuredate", dateTimeStr.get(0) );
        mv.addObject("departuretime", dateTimeStr.get(1) );
        mv.addObject("departurestation", departurestation);
        mv.addObject("arrivalstation", arrivalstation);
        mv.addObject("traincode", traincode);
        return mv;
    }
    @Secured("ROLE_USER")
    @PostMapping(Routes.buyTicket)
    @ResponseBody
    public ModelAndView payTicket(@RequestParam String familyname, @RequestParam String firstname, @RequestParam String birthdate, @RequestParam(value = "SeatNumber", required=false) String SeatNumber
        , @RequestParam String traincode, @RequestParam String fromSt, @RequestParam String toSt, @RequestParam String depdate , @RequestParam String deptime
        , HttpServletRequest request, HttpServletResponse response, @Valid PassengerForm passengerForm, BindingResult result) {
        // if we here then we need to booking a number and store order in the db

        logger.debug("TicketController payTicket method was called");
        ModelAndView mv = new ModelAndView();

        if(result.hasErrors()) {
            mv.setViewName("ticketprocess");
            //returnVal = "form";
        }
        else{
            mv.setViewName("ticketIsPurchased");
            String msg = bookService.payTicketProcess(familyname,firstname,birthdate,traincode,fromSt,toSt,depdate,deptime,request);
            mv.addObject("msg", msg);
        }

        return mv;

    }

    @ExceptionHandler(RouteNotExistingException.class)
    public ModelAndView handleRouteNotExistingException(HttpServletRequest request,
                                                        Exception ex) {
        logger.error("Requested URL=" + request.getRequestURL());
        logger.error("Exception Raised=" + ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("route", "This route is not exist");
        modelAndView.addObject("url", "This route is not exist");
        modelAndView.setViewName("error");

        return modelAndView;
    }

}
