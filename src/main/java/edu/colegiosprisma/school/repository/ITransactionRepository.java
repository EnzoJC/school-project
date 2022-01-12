package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findByEnrollmentAndState(Enrollment enrollment, State state);
}