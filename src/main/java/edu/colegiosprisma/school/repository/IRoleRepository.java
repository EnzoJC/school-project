package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
