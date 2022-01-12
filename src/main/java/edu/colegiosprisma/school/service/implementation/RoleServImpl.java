package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    public RoleServImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
