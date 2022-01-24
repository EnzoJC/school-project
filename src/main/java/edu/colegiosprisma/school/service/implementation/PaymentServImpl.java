package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.TypeBirth;
import edu.colegiosprisma.school.repository.IPaymentRepository;
import edu.colegiosprisma.school.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PaymentServImpl implements IPaymentService {
    private final IPaymentRepository paymentRepository;

    public PaymentServImpl(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
}
