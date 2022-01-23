package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student create(Student student, Enrollment enrollment);

    Optional<User> findById(String studentId);

    Boolean verifyDuplicate(Student student);
}
