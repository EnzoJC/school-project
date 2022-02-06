package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.Optional;
import java.util.Set;

public interface IEnrollmentService {
    Enrollment create(Enrollment enrollment, Student student);

    Enrollment updateStatusForNewStudent(Student student, int status);

    Enrollment updateStatusForOldStudent(Student student, int status);

    Optional<Enrollment>  findById(int id);

    Set<Enrollment> getAll();

    void deleteById(int id);

    Enrollment update(Enrollment enrollment, int id);
}
