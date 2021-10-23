package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    PaymentType findByName(String name);
}