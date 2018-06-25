package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
public class WebServicesController {
    private static final Logger logger = LoggerFactory.getLogger(WebServicesController.class);

    @RequestMapping(value = "/webService/{dateF}/{dateT}", method = RequestMethod.GET)
    public String getTickets(@PathVariable String dateF, @PathVariable String dateT) {

        logger.debug("WebServicesController 1");
        System.out.println("WebServicesController 2");
        return "WebServicesController called";
    }

    @RequestMapping(value = "/webService1", method = RequestMethod.GET)
    public String webService1() {

        logger.debug("webService1 1");
        System.out.println("webService1 2");
        return "webService1 1";
    }

}