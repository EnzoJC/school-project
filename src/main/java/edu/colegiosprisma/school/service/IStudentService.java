package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student createStudent(Student student, Enrollment enrollment);
    // getStudentById permite obtener un estudiante por su id
    Optional<User> getStudentById(String studentId);
    List<Integer> verifyStudentDuplicate(Student student);
}
