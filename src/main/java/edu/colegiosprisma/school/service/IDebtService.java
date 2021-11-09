package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Debt;
import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.Transaction;

public interface IDebtService {
    Debt createDebt(Transaction transaction, Payment payment);
    Debt update(String studentId, String paymentId);
}
