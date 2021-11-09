package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Enrollment enrollment, List<Payment> payments);
    Transaction updateStatus(Transaction transaction);
}
