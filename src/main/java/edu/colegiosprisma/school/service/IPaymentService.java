package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.TypeBirth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IPaymentService {
    List<Payment> getAll();
}
