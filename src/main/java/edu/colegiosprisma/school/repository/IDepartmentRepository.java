package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDepartmentRepository extends JpaRepository<Department, String> {
}