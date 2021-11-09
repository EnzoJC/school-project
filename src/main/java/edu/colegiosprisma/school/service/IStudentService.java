package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;

import java.util.Optional;

public interface IStudentService {
    Student createStudent(Student student, Enrollment enrollment);
    Optional<User> getStudentById(String studentId);
}
