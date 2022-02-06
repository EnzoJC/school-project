package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserService {
    Optional<User> findById(String id);
    User findByUsername(String user);
    Set<User> getAll();
    void DeleteById(String id);
    User update(User user, String id);
}
