package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDebtRepository extends JpaRepository<Debt, Integer> {
//    Debt findByEnrollmentAndPayment(Enrollment enrollment, Payment payment);
}