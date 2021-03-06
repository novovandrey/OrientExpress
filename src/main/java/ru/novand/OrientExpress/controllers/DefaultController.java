package ru.novand.OrientExpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/trainRouteList";
        }
        return "redirect:/schedule";
    }


    @RequestMapping("/")
    public String rootRedirect (HttpServletRequest request) {
        return "redirect:/schedule";
    }
}
