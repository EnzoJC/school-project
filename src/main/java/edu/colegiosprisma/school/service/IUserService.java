package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {
    User findByUsername(String user);
    Parent create(Parent parent);
}
