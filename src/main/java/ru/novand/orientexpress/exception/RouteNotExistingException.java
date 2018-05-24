package ru.novand.orientexpress.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Route Not Exist") //404
public class RouteNotExistingException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public RouteNotExistingException(String depstation,String arrstatin, String traincode, String depdate){
        super("Attention! Next route: depstation,arrstation, not existing ");
    }
}