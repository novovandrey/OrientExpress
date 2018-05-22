package ru.novand.OrientExpress.controllers;

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

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {

        return "404";//this is view name
    }
}
