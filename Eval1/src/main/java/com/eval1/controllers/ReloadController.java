package com.eval1.controllers;

import com.eval1.models.appUser.AppUser;
import com.eval1.models.loan.LoanFilter;
import com.eval1.models.loan.LoanInput;
import com.eval1.models.loan.LoanRequest;
import com.eval1.models.reload.ReloadFilter;
import com.eval1.models.reload.ReloadInput;
import com.eval1.models.reload.ReloadRequest;
import com.eval1.security.SecurityManager;
import com.eval1.services.ReloadRequestService;
import com.eval1.services.StatusService;
import custom.springutils.util.ListResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/reload")
public class ReloadController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private HttpSession session;

    @Autowired
    private ReloadRequestService reloadRequestService;

    @Autowired
    private StatusService statusService;


    @GetMapping("/request/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isCLient();
        modelAndView.setViewName("reload/form-request");
        return modelAndView;
    }

    @PostMapping("/request")
    public ResponseEntity<?> save(@ModelAttribute ReloadInput reloadInput) throws Exception {
        securityManager.isCLient();
        try {
            ReloadRequest request = reloadInput.getReloadRequest();
            AppUser appUser = (AppUser) session.getAttribute("appUser");
            request.setClient(appUser);
            reloadRequestService.create(request);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/requests")
    public ModelAndView list(ModelAndView modelAndView, ReloadFilter reloadFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isConnected();
        if (page == null) page = 1;
        AppUser appUser = (AppUser) session.getAttribute("appUser");
        if (!appUser.isAdmin())
            reloadFilter.setClient(appUser);
        ListResponse requests = reloadRequestService.search(reloadFilter, page);
        modelAndView.addObject("requiredPages", reloadRequestService.getRequiredPages(requests.getCount()));
        modelAndView.addObject("requests",requests.getElements());
        modelAndView.addObject("status", statusService.findAll());
        modelAndView.addObject("page", page);
        if (reloadFilter != null) modelAndView.addObject("reloadFilter", reloadFilter);
        modelAndView.setViewName("reload/list-requests");
        return modelAndView;
    }

    @PostMapping("accept/{id}")
    public ResponseEntity<?> accept(@PathVariable Long id, @RequestParam(name = "date") String date) throws Exception {
        securityManager.isAdmin();
        try {

            reloadRequestService.accept(id, java.sql.Date.valueOf(date));
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("refuse/{id}")
    public ResponseEntity<?> refuse(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            reloadRequestService.updateStatus(id, 10L);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("restore/{id}")
    public ResponseEntity<?> restore(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            reloadRequestService.updateStatus(id, 0L);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}
