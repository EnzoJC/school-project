package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}