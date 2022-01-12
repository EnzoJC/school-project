package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
