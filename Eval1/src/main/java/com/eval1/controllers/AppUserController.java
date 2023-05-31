package com.eval1.controllers;

import com.eval1.models.appUser.AppUser;
import com.eval1.models.appUser.AppUserFilter;
import com.eval1.models.duration.DurationFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.AppUserService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        AppUser appUser = appUserService.findById(id);
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("app-user/form");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute AppUser appUser, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {
            appUser.setId(id);
            appUserService.update(appUser);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, AppUserFilter appUserFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse users = appUserService.search(appUserFilter, page);
        modelAndView.addObject("requiredPages", appUserService.getRequiredPages(users.getCount()));
        modelAndView.addObject("users",users.getElements());
        modelAndView.addObject("page", page);
        if (appUserFilter != null) modelAndView.addObject("appUserFilter", appUserFilter);
        modelAndView.setViewName("app-user/list");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            appUserService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
