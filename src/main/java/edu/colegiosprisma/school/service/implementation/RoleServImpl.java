package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
