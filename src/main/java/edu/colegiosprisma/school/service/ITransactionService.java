package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.*;
import java.util.Set;

public interface ITransactionService {
    Transaction createTransaction(Enrollment enrollment, Set<Payment> payments);
    Boolean payTransaction(Student student, State state);
}
