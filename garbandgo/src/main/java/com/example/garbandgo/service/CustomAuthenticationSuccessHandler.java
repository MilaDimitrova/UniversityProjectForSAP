package com.example.garbandgo.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "/";
        var authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            switch (role) {
                case "ROLE_ADMIN":
                    redirectUrl = "/admin/dashboard";
                    break;
                case "ROLE_USER":
                    redirectUrl = "/user/userPage";
                    break;
                case "ROLE_COURIER":
                    redirectUrl = "/courier/dashboard";
                    break;
                case "ROLE_REST_OWNER":
                    redirectUrl = "/rest-owner/dashboard";
                    break;
                case "ROLE_MANAGER":
                    redirectUrl = "/manager/dashboard";
                    break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
