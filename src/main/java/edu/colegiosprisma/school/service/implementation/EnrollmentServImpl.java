package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.SchoolYear;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.repository.IEnrollmentRepository;
import edu.colegiosprisma.school.repository.ISchoolYearRepository;
import edu.colegiosprisma.school.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EnrollmentServImpl implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    private ISchoolYearRepository schoolYearRepository;

    @Override
    public Enrollment create(Enrollment enrollment, Student student) {
        LocalDate date = LocalDate.now();
        int currentYear = date.getYear();
        SchoolYear schoolYear = schoolYearRepository.findByYear(currentYear);

        enrollment.setSchoolYear(schoolYear);
        enrollment.setStudent(student);
        enrollment.setEnrollmentStatus(false);
        enrollment.setCurrentYear(true);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment updatePayment(Student student) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        enrollment.setEnrollmentStatus(true);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }
}
