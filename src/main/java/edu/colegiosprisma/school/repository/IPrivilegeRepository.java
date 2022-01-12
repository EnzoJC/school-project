package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeRepository extends JpaRepository<Privilege, Integer> {
}
