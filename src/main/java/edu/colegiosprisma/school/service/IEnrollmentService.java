package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Student;

public interface IEnrollmentService {
    Enrollment create(Enrollment enrollment, Student student);
    Enrollment updatePayment(Student student);
}
