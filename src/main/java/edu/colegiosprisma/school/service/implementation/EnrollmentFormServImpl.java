package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.*;
import edu.colegiosprisma.school.service.IEnrollmentFormService;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentFormServImpl implements IEnrollmentFormService {
    private final IEnrollmentFormRepository enrollmentFormRepository;


    @Autowired
    public EnrollmentFormServImpl(IEnrollmentFormRepository enrollmentFormRepository) {
        this.enrollmentFormRepository = enrollmentFormRepository;
    }

    @Override
    public EnrollmentForm create(EnrollmentForm enrollment,Student student) {
        enrollment.setStudent(student);
        enrollment.setStudentCode(student.getDocumentNumber());
        enrollmentFormRepository.save(enrollment);
        return enrollment;
    }

    @Override
    public Optional<EnrollmentForm> findById(String id) {
        return enrollmentFormRepository.findById(id);
    }

    @Override
    public Set<EnrollmentForm> getAll() {
        return new HashSet<>(enrollmentFormRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        enrollmentFormRepository.deleteById(id);
    }

    @Override
    public EnrollmentForm update(EnrollmentForm enrollment, String id) {
        return null;
    }
}
