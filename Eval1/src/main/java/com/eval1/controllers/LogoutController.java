package com.eval1.controllers;

import com.eval1.exception.UnauthorizedException;
import com.eval1.models.AppUser;
import com.eval1.security.SecurityManager;
import com.eval1.services.AppUserLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    AppUserLoginService appUserLoginService;

    @Autowired
    SecurityManager securityManager;

    @GetMapping({"/logout"})
    public String logout(HttpSession session) throws UnauthorizedException {
        securityManager.isConnected();
        Object obj = session.getAttribute("connected");
        if (obj instanceof AppUser) {
            this.appUserLoginService.logout("");
        }
        return "redirect:/login";
    }

}
