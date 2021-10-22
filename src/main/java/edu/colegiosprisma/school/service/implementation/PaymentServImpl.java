package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.repository.IPaymentRepository;
import edu.colegiosprisma.school.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServImpl implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }
}
