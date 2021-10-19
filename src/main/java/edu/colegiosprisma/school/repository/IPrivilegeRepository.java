package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IPrivilegeRepository extends JpaRepository<Privilege, Integer> {
}
