package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.IEnrollmentRepository;
import edu.colegiosprisma.school.repository.ISchoolRepository;
import edu.colegiosprisma.school.repository.IStateRepository;
import edu.colegiosprisma.school.repository.ITransactionRepository;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionServImpl implements ITransactionService {
    private final ISchoolRepository schoolRepository;
    private final IStateRepository stateRepository;
    private final ITransactionRepository transactionRepository;
    private final IEnrollmentRepository enrollmentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TransactionServImpl(ISchoolRepository schoolRepository,
                               IStateRepository stateRepository, ITransactionRepository transactionRepository,
                               IEnrollmentRepository enrollmentRepository) {
        this.schoolRepository = schoolRepository;
        this.stateRepository = stateRepository;
        this.transactionRepository = transactionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Transaction create(Enrollment enrollment, Set<Payment> payments) {
        Transaction transaction = new Transaction();
        transaction.setEnrollment(enrollment);
        transaction.setIssueDate(LocalDate.now());
        transaction.setExpirationDate(LocalDate.now().plusDays(3));
        transaction.setSchool(schoolRepository.findById("20600093470").isPresent() ? schoolRepository.findById("20600093470").get() : new School());
        transaction.setState(stateRepository.findById(5).isPresent() ? stateRepository.findById(5).get() : new State());

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_trans_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        query.execute();

        String id = (String) query.getSingleResult();
        transaction.setId(id);
        // Recorriendo el Set de payments para crear cada uno de los pagos
//        for (Payment payment : payments) {
//            System.out.println("Payment 1");
//            transaction.addPayment(payment);
//            System.out.println("Payment 2");
//        }
        transaction.setPayments(payments);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Boolean pay(Student student, State state, String description) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        Transaction transaction = transactionRepository.findByEnrollmentAndState(enrollment, state);
        if (transaction != null) {
            updateTransaccionAsPaid(transaction);
            transaction.setPaymentDate(LocalDate.now());
            transaction.setDescription(description);
            transactionRepository.save(transaction);
            return true;
        }
        return false;
    }

    @Override
    public void updateTransaccionAsPaid(Transaction transaction) {
        transaction.setState(stateRepository.findById(7).isPresent() ? stateRepository.findById(7).get() : new State()); // 7 = pagado
    }

    @Override
    public Optional<Transaction> getById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Set<Transaction> getAll() {
        return new LinkedHashSet<>(transactionRepository.findAll());
    }

    @Override
    public Transaction update(Transaction transaction, String id) {
        return null;
    }
}
