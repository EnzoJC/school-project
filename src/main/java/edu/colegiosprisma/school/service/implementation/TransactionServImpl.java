package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.Transaction;
import edu.colegiosprisma.school.repository.ISchoolRepository;
import edu.colegiosprisma.school.repository.IStateRepository;
import edu.colegiosprisma.school.repository.ITransactionRepository;
import edu.colegiosprisma.school.service.IDebtService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServImpl implements ITransactionService {
    private final IDebtService debtService;
    private final ISchoolRepository schoolRepository;
    private final IStateRepository stateRepository;
    private final ITransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransactionServImpl(IDebtService debtService, ISchoolRepository schoolRepository,
                               IStateRepository stateRepository, ITransactionRepository transactionRepository) {
        this.debtService = debtService;
        this.schoolRepository = schoolRepository;
        this.stateRepository = stateRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Enrollment enrollment, List<Payment> payments) {
        Transaction transaction = new Transaction();
        transaction.setEnrollment(enrollment);
        transaction.setIssueDate(LocalDate.now());
        transaction.setExpirationDate(LocalDate.now().plusDays(3));
        transaction.setSchool(schoolRepository.findById("20600093470").get());
        transaction.setState(stateRepository.findById(5).get());

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_generate_trans_id");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        query.execute();

        String id = (String) query.getSingleResult();
        transaction.setId(id);
        transactionRepository.save(transaction);

        for (int i = 0; i < payments.size(); i++) {
            debtService.createDebt(transaction, payments.get(i));
        }

        return transaction;
    }

    @Override
    public Transaction updateStatus(Transaction transaction) {
        return null;
    }
}
