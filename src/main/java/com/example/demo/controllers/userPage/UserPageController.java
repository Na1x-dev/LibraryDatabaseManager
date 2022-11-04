package com.example.demo.controllers.userPage;

import com.example.demo.controllers.user.UserValidator;
import com.example.demo.models.Sale;
import com.example.demo.models.User;
import com.example.demo.security.SecurityService;
import com.example.demo.services.user.UserService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller
public class UserPageController {
    @Autowired
    UserService userService;
    @Autowired
    UserValidator userValidator;
    @Autowired
    SecurityService securityService;

    @GetMapping({"/userPage/index"})
    public String userPage(Model model, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("users", userService.readAll());
        model.addAttribute("updateUser", new User());
        return "userPage/index";
    }

    @GetMapping({"/newUserPage/index"})
    public String newUserPage(Model model, Principal user) {
        User newUser = new User();
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        model.addAttribute("newUser", newUser);
        return "newUserPage/index";
    }

    @PostMapping({"/newUserPage/index"})
    public String newUserPage(Model model, @ModelAttribute("newUser") User newUser, BindingResult bindingResult, Principal user) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        newUser.setPasswordConfirm(newUser.getPassword());
        userValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            return "newUserPage/index";
        }
        userService.create(newUser);
//        securityService.autoLogin(newUser.getUsername(), newUser.getPasswordConfirm());
        return "redirect:/mainPage/index";
    }

    @PostMapping("/userPage/index/update/{id}")
    public String updateUser(Model model, @ModelAttribute("updateUser") User updateUser, BindingResult bindingResult, Principal user, @PathVariable("id") Long userId) {
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
        updateUser.setPasswordConfirm(updateUser.getPassword());
        userValidator.validate(updateUser, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/userPage/index";
        }
        if (!Objects.equals(userService.findByUsername("admin").getUserId(), userId)) {
            userService.update(updateUser, userId);
        }
        return "redirect:/userPage/index";
    }

    @GetMapping("/userPage/index/delete/{id}")
    public String deleteUser(Model model, Principal user, @PathVariable("id") Long userId) {
        if (!Objects.equals(userService.findByUsername("admin").getUserId(), userId)) {
            userService.delete(userId);
        }
//        model.addAttribute("users", userService.readAll());
        model.addAttribute("checkUser", userService.findByUsername(user.getName()));
//        model.addAttribute("updateUser", new User());
        return "redirect:/userPage/index";
    }

}
