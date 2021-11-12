package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.*;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Enrollment enrollment, List<Payment> payments);
    Boolean payTransaction(Student student, State state);
}
