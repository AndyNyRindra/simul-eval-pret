package com.eval1.services;

import com.eval1.authentication.SessionLoginService;
import com.eval1.models.appUser.AppUser;
import com.eval1.repositories.AppUserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


@Service
public class AppUserLoginService extends SessionLoginService<AppUser, AppUserRepo> {
    public AppUserLoginService(AppUserRepo repo, HttpSession session) {
        super(repo, "appUser", session);
    }
}
