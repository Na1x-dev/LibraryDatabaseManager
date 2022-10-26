package com.example.demo.controllers.user;

import com.example.demo.models.User;
import com.example.demo.security.SecurityService;


import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/signUpPage/index")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        User user = new User();

        model.addAttribute("userForm", user);

        return "signUpPage/index";
    }

    @PostMapping("/signUpPage/index")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signUpPage/index";
        }
        userService.create(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/mainPage/index";
    }

    @GetMapping("/logInPage/index")
    public String login(Model model, String error, String logout) {
        autoRegisterAdmin();
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "logInPage/index";
    }

    @GetMapping("/logout")
    public String logout(Model model, String error, String logout) {
        return "logInPage/index";
    }

    @GetMapping({"/"})
    public String startPage(Model model) {
        return "redirect:mainPage/index";
    }

    public void autoRegisterAdmin() {
        if (userService.findByUsername("admin") == null) {
            User admin = new User("admin", "admin", new HashSet<>(), "admin");
            userService.create(admin);
            securityService.autoLogin(admin.getUsername(), admin.getPassword());
        }
    }
}