package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.User;

public interface IUserService {
    public User findByUsername(String user);
}
