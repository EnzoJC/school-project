package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IParentService {
    Parent create(Parent parent);
//    List<User> selectAll();
//    User selectByUsername(String username);
}
