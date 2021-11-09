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
import javax.persistence.PersistenceContext;

@Service
public class DebtServImpl implements IDebtService {
    private final IPaymentRepository paymentRepository;
    private final IStudentRepository studentRepository;
    private final IEnrollmentRepository enrollmentRepository;
    private final IDebtRepository debtRepository;

    @Autowired
    public DebtServImpl(IPaymentRepository paymentRepository, IStudentRepository studentRepository,
                        IEnrollmentRepository enrollmentRepository, IDebtRepository debtRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.debtRepository = debtRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Debt createDebt(Transaction transaction, Payment payment) {
        Debt debt = new Debt();
        debt.setPayment(payment);
        debt.setTransaction(transaction);
        return debtRepository.save(debt);
    }

    @Override
    public Debt update(String StudentId, String paymentId) {
//        Optional<User> student = studentRepository.findById(StudentId);
//        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue((Student) student.get());
//        Optional<Payment> payment = paymentRepository.findById(Integer.parseInt(paymentId));
//
//        Debt debt = debtRepository.findByEnrollmentAndPayment(enrollment, payment.get());
//
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_billing_id");
//        query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
//        query.execute();
//
//        Integer payment_id = Integer.parseInt(query.getSingleResult().toString());
//
//        debt.setBillingId(1000 + payment_id);
//        debt.setPaymentDate(LocalDate.now());
//        debt.setPaymentStatus(true);
//        debtRepository.save(debt);

        return null;
    }
}
