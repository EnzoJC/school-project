package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;

import java.util.List;

public interface IParentService {
    Parent create(Parent parent);

    List<Integer> verifyDuplicate(Parent parent);

    Parent findByUsername(String username);

    Parent update(Parent parent, String id);
}
