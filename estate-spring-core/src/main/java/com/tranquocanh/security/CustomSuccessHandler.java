package com.tranquocanh.security;

import com.tranquocanh.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl();

        if (response.isCommitted()) {
            System.out.println("can't redirect url");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl() {
        String url = "";
        List<String> roles = SecurityUtils.getAuthorities();
        if (isStaff(roles)) {
            url = "/admin/home";
        } else if (isManager(roles)) {
            url = "/admin/home";
        }
        return url;
    }

    private boolean isManager(List<String> roles) {
        if (roles.contains("MANAGER")) {
            return true;
        }
        return false;
    }

    private boolean isStaff(List<String> roles) {
        if (roles.contains("STAFF")) {
            return true;
        }
        return false;
    }
}
