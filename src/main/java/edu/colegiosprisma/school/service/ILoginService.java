package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.entity.Tutor;

public interface ILoginService {
    public Login findByUser(String user);
    public Login registrar(Login login);
}
