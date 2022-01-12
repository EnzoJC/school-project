package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.*;

import java.util.Set;

public interface ITransactionService {
    Transaction create(Enrollment enrollment, Set<Payment> payments);

    Boolean pay(Student student, State state);
}
