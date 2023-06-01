package com.eval1.controllers;

import com.eval1.models.appUser.AppUser;
import com.eval1.models.loan.*;
import com.eval1.security.SecurityManager;
import com.eval1.services.*;
import custom.springutils.util.ListResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private LoanRequestService loanRequestService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private DurationService durationService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ReimbursementService reimbursementService;

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

    @GetMapping("/requests")
    public ModelAndView listRequests(ModelAndView modelAndView, LoanRequestFilter loanFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isConnected();
        if (page == null) page = 1;
        AppUser appUser = (AppUser) session.getAttribute("appUser");
        if (!appUser.isAdmin())
            loanFilter.setClient(appUser);
        ListResponse requests = loanRequestService.search(loanFilter, page);
        modelAndView.addObject("requiredPages", loanRequestService.getRequiredPages(requests.getCount()));
        modelAndView.addObject("requests",requests.getElements());
        modelAndView.addObject("status", statusService.findAll());
        modelAndView.addObject("page", page);
        if (loanFilter != null) modelAndView.addObject("loanFilter", loanFilter);
        modelAndView.setViewName("loan/list-requests");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, LoanFilter loanFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isConnected();
        if (page == null) page = 1;
        AppUser appUser = (AppUser) session.getAttribute("appUser");
        if (!appUser.isAdmin())
            loanFilter.setClient(appUser);
        ListResponse requests = loanService.search(loanFilter, page);
        modelAndView.addObject("requiredPages", loanService.getRequiredPages(requests.getCount()));
        modelAndView.addObject("requests",requests.getElements());
        modelAndView.addObject("page", page);
        if (loanFilter != null) modelAndView.addObject("loanFilter", loanFilter);
        modelAndView.setViewName("loan/list");
        return modelAndView;
    }

    @GetMapping("{id}/reimbursements")
    public ModelAndView listReimbursements(ModelAndView modelAndView, ReimbursementFilter reimbursementFilter, @RequestParam(required = false) Integer page, @PathVariable Long id) throws Exception {
        securityManager.isConnected();
        if (page == null) page = 1;
        Loan loan = loanService.findById(id);
        reimbursementFilter.setLoan(loan);
        ListResponse reimbursements = reimbursementService.search(reimbursementFilter, page);
        modelAndView.addObject("requiredPages", reimbursementService.getRequiredPages(reimbursements.getCount()));
        modelAndView.addObject("reimbursements",reimbursements.getElements());
        modelAndView.addObject("loan", loan);
        modelAndView.addObject("rateSum", reimbursementService.getSumRate((List<Reimbursement>) reimbursements.getElements()));
        modelAndView.addObject("page", page);
        if (reimbursementFilter != null) modelAndView.addObject("reimbursementFilter", reimbursementFilter);
        modelAndView.setViewName("loan/reimbursements");
        return modelAndView;
    }

    @PostMapping("accept/{id}")
    public ResponseEntity<?> accept(@PathVariable Long id, @RequestParam(name = "date") String date, @RequestParam(name = "startReimbursement") String startReimbursement) throws Exception {
        securityManager.isAdmin();
        try {

            loanRequestService.accept(id, java.sql.Date.valueOf(date), java.sql.Date.valueOf(startReimbursement));
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/refuse/{id}")
    public ResponseEntity<?> refuse(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            loanRequestService.updateStatus(id, 10L);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
