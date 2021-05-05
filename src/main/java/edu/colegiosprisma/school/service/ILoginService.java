package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Login;

public interface ILoginService {
    public Login findByUser(String user);
}
