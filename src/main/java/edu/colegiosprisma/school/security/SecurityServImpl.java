package edu.colegiosprisma.school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public class SecurityServImpl implements ISecurityService{

    @Override
    public boolean isAuthenticated() {
        // aun no esta implementado
        return false;
    }
}
