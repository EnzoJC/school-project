package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, String> {
}