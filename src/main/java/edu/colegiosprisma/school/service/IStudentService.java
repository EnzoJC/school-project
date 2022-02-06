package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.Set;

public interface IStudentService {
    Student create(Student student, Enrollment enrollment);

    Boolean isDuplicate(Student student, Model model);
    public Boolean isDuplicateDocumentNumber(String documentNumber);

    Optional<User> findById(String studentId);

    Boolean verifyDuplicate(Student student);

    Student findByUsername(String user);
    Set<Student> getAll();
    void deleteById(String id);
    Student update(Student student, String id);
}
