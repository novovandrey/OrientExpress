package ru.novand.orientexpress.controllers;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.novand.orientexpress.domain.dto.UserDTO;


@Controller
public class UserController_old_work {

   @GetMapping("/userForm")
   public String userForm(Locale locale,Model model) {
      model.addAttribute("user", new UserDTO());
      return "userForm";
   }

   @PostMapping("/saveUser")
   public String saveUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult result,
         Model model) {

      if (result.hasErrors()) {
         return "userForm";
      }
      return "success";
   }
}
