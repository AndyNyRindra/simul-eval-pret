package com.eval1.security;

import com.eval1.exception.UnauthorizedException;
import com.eval1.models.AppUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager {

    HttpSession session;

    public SecurityManager(HttpSession session) {
        this.session = session;
    }

    public void isConnected() throws UnauthorizedException {
        check("connected", null);
    }

    public void isAdmin() throws UnauthorizedException {
        isConnected();
        check("appUser", 10);
    }

    public void isAuthor() throws UnauthorizedException {
        isConnected();
        check("appUser", 0);
    }

    private void check(String role, Integer level) throws UnauthorizedException {
        if (session.getAttribute(role) != null) {
            if(level != null) {
                if(((AppUser) session.getAttribute(role)).getRole().getId().equals((long)level)) {
                    return;
                } else {
                    throw new UnauthorizedException();
                }
            } else {
                return;
            }
        }
        throw new UnauthorizedException();
    }

}
