package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.Optional;
import java.util.Set;

public interface IRoleService {
    Role findByName(String name);

    Optional<Role>  findById(int id);

    Set<Role> getAll();

    void deleteById(int id);

    Role update(Role role, int id);
}
