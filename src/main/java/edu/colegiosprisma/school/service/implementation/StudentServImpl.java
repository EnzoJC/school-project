package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.*;
import edu.colegiosprisma.school.service.IEnrollmentService;

import edu.colegiosprisma.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServImpl implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private IPaymentTypeRepository paymentTypeRepository;

    @Autowired
    private IDebtRepository paymentEnrollmentRepository;

    @Autowired
    private IEnrollmentService enrollmentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student create(Student student, Enrollment enrollment) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        query.setParameter(1, "Student");
        query.execute();

        String id = (String) query.getSingleResult();

        student.setId(id);
        student.setType("Student");
        student.setUsername(id);
        student.setPassword(new BCryptPasswordEncoder(4).encode(student.getDocumentNumber()));
        student.setStudentEmail(id + "@colegiosprisma.edu.pe");
        List<Role> listaRolesStudent = new ArrayList<>();
        Role auxRole = roleRepository.findByName("ROLE_STUDENT");
        listaRolesStudent.add(auxRole);
        student.setRoles(listaRolesStudent);

        Debt paymentEnrollment = new Debt();

        Payment payment = paymentRepository.findByPaymentTypeAndCurrentYearIsTrue(paymentTypeRepository.findByName("Matrícula"));
        paymentEnrollment.setPayment(payment);

        Enrollment e = enrollmentService.create(enrollment, studentRepository.save(student));

        paymentEnrollment.setEnrollment(e);
        paymentEnrollment.setPaymentStatus(false);
        paymentEnrollment.setExpirationDate(LocalDate.now().plusDays(3));

        paymentEnrollmentRepository.save(paymentEnrollment);
        return student;
    }

    @Override
    public Optional<User> getStudentById(String studentId) {
        return studentRepository.findById(studentId);
    }
}
