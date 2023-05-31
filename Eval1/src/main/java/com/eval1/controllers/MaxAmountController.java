package com.eval1.controllers;

import com.eval1.models.duration.DurationFilter;
import com.eval1.models.maxAmount.MaxAmount;
import com.eval1.models.maxAmount.MaxAmountFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.MaxAmountService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/max-amount")
public class MaxAmountController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    MaxAmountService maxAmountService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("max-amount/form");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute MaxAmount maxAmount) throws Exception {
        securityManager.isAdmin();
        try {
            maxAmountService.create(maxAmount);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, MaxAmountFilter maxAmountFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse maxAmounts = maxAmountService.search(maxAmountFilter, page);
        modelAndView.addObject("requiredPages", maxAmountService.getRequiredPages(maxAmounts.getCount()));
        modelAndView.addObject("maxAmounts",maxAmounts);
        modelAndView.addObject("page", page);
        if (maxAmountFilter != null) modelAndView.addObject("maxAmountFilter", maxAmountFilter);
        modelAndView.setViewName("max-amount/list");
        return modelAndView;
    }
}
