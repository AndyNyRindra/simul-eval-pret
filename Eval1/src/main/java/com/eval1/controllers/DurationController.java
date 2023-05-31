package com.eval1.controllers;

import com.eval1.models.Duration;
import com.eval1.security.SecurityManager;
import com.eval1.services.DurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/durations")
public class DurationController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    DurationService maxDurationService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("durations/form");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute Duration maxDuration) throws Exception {
        securityManager.isAdmin();
        try {
            maxDurationService.create(maxDuration);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Duration maxDuration = maxDurationService.findById(id);
        modelAndView.addObject("maxDuration", maxDuration);
        modelAndView.setViewName("durations/form");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute Duration maxDuration, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            maxDuration.setId(id);
            maxDurationService.update(maxDuration);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

//    @GetMapping
////    public ModelAndView list(ModelAndView modelAndView, BrandFilter brandFilter, @RequestParam(required = false) Integer page) throws Exception {
////        securityManager.isAdmin();
////        if (page == null) page = 1;
////        ListResponse brands = maxDurationService.search(brandFilter, page);
////        modelAndView.addObject("requiredPages", maxDurationService.getRequiredPages(brands.getCount()));
////        modelAndView.addObject("brands",brands);
////        modelAndView.addObject("page", page);
////        if (brandFilter != null) modelAndView.addObject("brandFilter", brandFilter);
////        modelAndView.setViewName("brands/list-brands");
////        return modelAndView;
////    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            maxDurationService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
