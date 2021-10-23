package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Debt;

public interface IDebtService {
    Debt create(Debt paymentEnrollment);
    Debt update(String studentId, String paymentId);
}
