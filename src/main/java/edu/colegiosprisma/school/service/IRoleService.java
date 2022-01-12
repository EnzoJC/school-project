package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Role;

public interface IRoleService {
    Role findByName(String name);
}
