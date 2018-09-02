package ru.novand.orientexpress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(Routes.home)
    public String defaultAfterLogin(HttpServletRequest request) {
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            return "redirect:/trainRouteList";
//        }
        return "redirect:/schedule";
    }

    @RequestMapping("/")
    public String rootRedirect (HttpServletRequest request) {
        return "redirect:/schedule";
    }
}
