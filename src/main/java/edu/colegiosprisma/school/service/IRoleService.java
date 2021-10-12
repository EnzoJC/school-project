package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Role;

public interface IRoleService {
    // Obtener un rol por su nombre
    Role getRoleByName(String name);
}
