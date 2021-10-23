package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.IDebtRepository;
import edu.colegiosprisma.school.repository.IEnrollmentRepository;
import edu.colegiosprisma.school.repository.IPaymentRepository;
import edu.colegiosprisma.school.repository.IStudentRepository;
import edu.colegiosprisma.school.service.IDebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DebtServImpl implements IDebtService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    private IDebtRepository debtRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Debt create(Debt paymentEnrollment) {
        return debtRepository.save(paymentEnrollment);
    }

    @Override
    public Debt update(String StudentId, String paymentId) {
        Optional<User> student = studentRepository.findById(StudentId);
        Enrollment enrollment = enrollmentRepository.findByStudent((Student) student.get());
        Optional<Payment> payment = paymentRepository.findById(Integer.parseInt(paymentId));

        Debt debt = debtRepository.findByEnrollmentAndPayment(enrollment, payment.get());

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_payment_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        query.execute();

        Integer payment_id = Integer.parseInt(query.getSingleResult().toString());

        debt.setBillingId(1000 + payment_id);
        debt.setPaymentDate(LocalDate.now());
        debt.setPaymentStatus(true);

        return debt;
    }
}
