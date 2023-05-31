package com.eval1.controllers;

import com.eval1.models.AppUser;
import com.eval1.models.duration.Duration;
import com.eval1.security.SecurityManager;
import com.eval1.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app-user")
public class AppUserController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("app-user/form");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute AppUser appUser) throws Exception {
        securityManager.isAdmin();
        try {
            appUserService.create(appUser);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
