package com.eval1.controllers;

import com.eval1.models.appUser.AppUser;
import com.eval1.models.loan.LoanInput;
import com.eval1.models.loan.LoanRequest;
import com.eval1.models.maxAmount.MaxAmountInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.DurationService;
import com.eval1.services.LoanRequestService;
import custom.springutils.util.ListResponse;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private HttpSession session;

    @GetMapping("/request/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isCLient();
        ListResponse durations = durationService.search(new Object(), null);
        modelAndView.addObject("durations", durations.getElements());
        modelAndView.setViewName("loan/form-request");
        return modelAndView;
    }

    @PostMapping("/request")
    public ResponseEntity<?> save(@ModelAttribute LoanInput loanInput) throws Exception {
        securityManager.isCLient();
        try {
            LoanRequest loanRequest = loanInput.getLoanRequest();
            AppUser appUser = (AppUser) session.getAttribute("appUser");
            loanRequest.setClient(appUser);
            loanRequestService.create(loanRequest);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
