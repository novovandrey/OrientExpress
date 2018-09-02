package ru.novand.orientexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.novand.orientexpress.domain.entities.User;
import ru.novand.orientexpress.services.interfaces.SecurityService;
import ru.novand.orientexpress.services.interfaces.UserService;
import ru.novand.orientexpress.domain.DAO.Impl.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/home";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }

    @GetMapping("/userlk")
    public String userlk(Model model) {

        model.addAttribute("user", new User());

        return "userlk";
    }

}
