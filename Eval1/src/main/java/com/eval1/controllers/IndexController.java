package com.eval1.controllers;

import com.eval1.exception.UnauthorizedException;
import com.eval1.models.AppUser;
import com.eval1.security.SecurityManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private HttpSession session;

    @GetMapping({"/", "/admin", "/client"})
    public String index() throws UnauthorizedException {
        securityManager.isConnected();
        if (session.getAttribute("appUser") != null) {
            AppUser appUser = (AppUser) session.getAttribute("appUser");
            if (appUser.isAdmin())
                return "index";
            else
                return "index";
        }
        return null;
    }
}
