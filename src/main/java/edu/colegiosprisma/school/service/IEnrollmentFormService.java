package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.EnrollmentForm;
import edu.colegiosprisma.school.entity.Student;

import java.util.Optional;
import java.util.Set;

public interface IEnrollmentFormService {
    EnrollmentForm create(EnrollmentForm enrollment,Student student);

    Optional<EnrollmentForm>  findById(String id);

    Set<EnrollmentForm> getAll();

    void deleteById(String id);

    EnrollmentForm update(EnrollmentForm enrollment, String id);
}
