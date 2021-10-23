package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByPaymentTypeAndCurrentYearIsTrue(PaymentType paymentType);
}