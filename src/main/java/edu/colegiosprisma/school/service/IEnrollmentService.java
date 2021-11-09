package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Student;

public interface IEnrollmentService {
    Enrollment createEnrollment(Enrollment enrollment, Student student);
    Enrollment updateStatusForNewStudent(Student student, int status);
    Enrollment updateStatusForOldStudent(Student student, int status);
}
