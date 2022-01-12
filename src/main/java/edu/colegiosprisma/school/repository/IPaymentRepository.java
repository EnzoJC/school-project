package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    // Permite obtener el pago por tipo de pago y donde campo isActive sea true
    Payment findByPaymentTypeAndIsActiveIsTrue(PaymentType paymentType);
}