
package ru.novand.orientexpress.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @GetMapping(Routes.login)
    public String LoginProcess(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken ) {
            String referrer = request.getHeader("Referer");
            request.getSession().setAttribute("url_prior_login", referrer);
            return "form/login";
        }

        return request.getHeader("Referer");
    }

}
