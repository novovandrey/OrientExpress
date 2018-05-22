package ru.novand.OrientExpress.controllers;

//import ru.novand.OrientExpress.services.UserService;


public class UserController {

//    @Autowired
//    private UserService userService;
//
//        @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
//        public String showRegistrationForm(WebRequest request, Model model) {
//            UserDTO userDto = new UserDTO();
//            model.addAttribute("user", userDto);
//            return "registration";
//        }
//
//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDTO accountDto,
//            BindingResult result,
//            WebRequest request,
//            Errors errors) {
//
//        User registered = new User();
//        if (!result.hasErrors()) {
//            registered = createUserAccount(accountDto, result);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if (result.hasErrors()) {
//            return new ModelAndView("registration", "user", accountDto);
//        }
//        else {
//            return new ModelAndView("successRegister", "user", accountDto);
//        }
//    }
//    private User createUserAccount(UserDTO accountDto, BindingResult result) {
//        User registered = null;
//        try {
//            registered = userService.registerNewUserAccount(accountDto);
//        } catch (EmailExistsException e) {
//            return null;
//        }
//        return registered;
//    }

}

