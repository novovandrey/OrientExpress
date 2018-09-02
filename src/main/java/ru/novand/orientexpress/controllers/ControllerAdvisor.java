package ru.novand.orientexpress.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * exception handler which return the 404 page
 *
 * @author Andrey Novov
 * @return view 404
 */

@ControllerAdvice
public class ControllerAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        logger.error("Requested URL=" + "show 404 page");
        logger.error("Exception Raised=" + ex);
        return "404";//this is view name
    }
}
