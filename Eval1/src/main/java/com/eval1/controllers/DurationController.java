package com.eval1.controllers;

import com.eval1.models.duration.Duration;
import com.eval1.models.duration.DurationFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.DurationService;
import custom.springutils.util.ListResponse;
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
    DurationService durationService;

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
            durationService.create(maxDuration);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Duration duration = durationService.findById(id);
        modelAndView.addObject("duration", duration);
        modelAndView.setViewName("durations/form");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute Duration maxDuration, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            maxDuration.setId(id);
            durationService.update(maxDuration);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, DurationFilter durationFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse durations = durationService.search(durationFilter, page);
        modelAndView.addObject("requiredPages", durationService.getRequiredPages(durations.getCount()));
        modelAndView.addObject("durations",durations);
        modelAndView.addObject("page", page);
        if (durationFilter != null) modelAndView.addObject("durationFilter", durationFilter);
        modelAndView.setViewName("durations/list");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            durationService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
