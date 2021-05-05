package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class ILoginServImpl implements ILoginService {
    @Autowired
    private ILoginService loginService;

    @Override
    public Login findByUser(String user) {
        return null;
    }
}
