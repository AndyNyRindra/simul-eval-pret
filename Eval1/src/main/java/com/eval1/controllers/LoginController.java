package com.eval1.controllers;

import com.eval1.models.appUser.AppUser;
import com.eval1.services.AppUserLoginService;
import custom.springutils.LoginEntity;
import custom.springutils.service.ServiceLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AppUserLoginService appUserLoginService;



    @GetMapping({"","/admin", "/client"})
    public String loginView(HttpSession session) {
        if (session.getAttribute("connected") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping
    public Object login(@ModelAttribute AppUser appUser) {
        return doLogin(appUser, appUserLoginService);
    }

    private <T extends LoginEntity> Object doLogin (T entity, ServiceLogin<T> service) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            service.login(entity);
            return "redirect:/";
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("email", entity.getEmail());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

}

