package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.repository.IRoleRepository;
import edu.colegiosprisma.school.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Set<Role> getAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role, int id) {
        return null;
    }
}
