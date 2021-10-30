package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;

public interface IParentService {
    Parent create(Parent parent);
//    List<User> selectAll();
    Parent selectByUsername(String username);
    Parent update(Parent parent);
}
