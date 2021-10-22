package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
}