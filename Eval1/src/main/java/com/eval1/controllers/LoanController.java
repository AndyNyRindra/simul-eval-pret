package com.eval1.controllers;

import com.eval1.models.maxAmount.MaxAmountInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.DurationService;
import com.eval1.services.LoanRequestService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private LoanRequestService loanRequestService;

    @Autowired
    private DurationService durationService;

    @GetMapping("/request/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isCLient();
        ListResponse durations = durationService.search(new Object(), null);
        modelAndView.addObject("durations", durations.getElements());
        modelAndView.setViewName("loan/form-request");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute MaxAmountInput maxAmount) throws Exception {
        securityManager.isAdmin();
        try {
            maxAmountService.create(maxAmount.getMaxAmount());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
