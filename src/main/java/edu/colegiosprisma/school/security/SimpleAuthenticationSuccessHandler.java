package edu.colegiosprisma.school.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/parent");
        } else if (roles.contains("ROLE_TEACHER")) {
            httpServletResponse.sendRedirect("/teacher");
        } else if (roles.contains("ROLE_PARENT")) {
            httpServletResponse.sendRedirect("/parent");
        } else if (roles.contains("ROLE_STUDENT")) {
            httpServletResponse.sendRedirect("/student");
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}
