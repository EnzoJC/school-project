package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.EnrollmentForm;
import edu.colegiosprisma.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnrollmentFormRepository extends JpaRepository<EnrollmentForm, String> {

}