package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.User;
import org.springframework.stereotype.Component;

public interface IUserService {
    User findByUsername(String user);
}
