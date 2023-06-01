package com.eval1.controllers;

import com.eval1.models.MonthYearFilter;
import com.eval1.models.loan.Loan;
import com.eval1.models.loan.Reimbursement;
import com.eval1.models.loan.ReimbursementFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.VBeneficeService;
import com.eval1.services.VTotalRemboursesService;
import com.eval1.util.DateUtil;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    VTotalRemboursesService vTotalRemboursesService;

    @Autowired
    VBeneficeService vBeneficeService;

    @GetMapping("totalRembourses")
    public ModelAndView listReimbursements(ModelAndView modelAndView, MonthYearFilter monthYearFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;

        ListResponse reimbursements = vTotalRemboursesService.search(monthYearFilter, page);
        modelAndView.addObject("requiredPages", vTotalRemboursesService.getRequiredPages(reimbursements.getCount()));
        modelAndView.addObject("reimbursements",reimbursements.getElements());
        modelAndView.addObject("page", page);
        modelAndView.addObject("months", DateUtil.getMonths());
        if (monthYearFilter != null) modelAndView.addObject("monthYearFilter", monthYearFilter);
        modelAndView.setViewName("stat/reimbursements");
        return modelAndView;
    }

    @GetMapping("benefices")
    public ModelAndView benefice(ModelAndView modelAndView, MonthYearFilter monthYearFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;

        ListResponse benefices = vBeneficeService.search(monthYearFilter, page);
        modelAndView.addObject("requiredPages", vBeneficeService.getRequiredPages(benefices.getCount()));
        modelAndView.addObject("benefices",benefices.getElements());
        modelAndView.addObject("page", page);
        modelAndView.addObject("months", DateUtil.getMonths());
        if (monthYearFilter != null) modelAndView.addObject("monthYearFilter", monthYearFilter);
        modelAndView.setViewName("stat/benefices");
        return modelAndView;
    }
}
