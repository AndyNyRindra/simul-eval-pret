package com.eval1.controllers;

import com.eval1.models.maxAmount.MaxAmountFilter;
import com.eval1.models.rate.Rate;
import com.eval1.models.rate.RateFilter;
import com.eval1.models.rate.RateInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.RateService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rates")
public class RateController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    RateService rateService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("rate/form");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute RateInput rate) throws Exception {
        securityManager.isAdmin();
        try {
            rateService.create(rate.getRate());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, RateFilter rateFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse rates = rateService.search(rateFilter, page);
        modelAndView.addObject("requiredPages", rateService.getRequiredPages(rates.getCount()));
        modelAndView.addObject("rates",rates);
        modelAndView.addObject("page", page);
        if (rateFilter != null) modelAndView.addObject("rateFilter", rateFilter);
        modelAndView.setViewName("rate/list");
        return modelAndView;
    }
}
