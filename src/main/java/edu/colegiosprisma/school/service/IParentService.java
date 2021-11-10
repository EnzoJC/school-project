package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;

import java.util.List;

public interface IParentService {
    Parent createParent(Parent parent);
    List<Integer> verifyParentDuplicate(Parent parent);

//    List<User> selectAll();
    Parent selectByUsername(String username);
    Parent update(Parent parent, String id);
}
