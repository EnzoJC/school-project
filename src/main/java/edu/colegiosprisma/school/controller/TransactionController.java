package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Payment;
import edu.colegiosprisma.school.entity.PaymentType;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.repository.IPaymentRepository;
import edu.colegiosprisma.school.repository.IPaymentTypeRepository;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
    private final ITransactionService transactionService;
    private final IEnrollmentService enrollmentService;
    private final IStudentService studentService;
    private final IPaymentRepository paymentRepository;
    private final IPaymentTypeRepository paymentTypeRepository;

    @Autowired
    public TransactionController(ITransactionService transactionService, IEnrollmentService enrollmentService,
                                 IStudentService studentService, IPaymentRepository paymentRepository,
                                 IPaymentTypeRepository paymentTypeRepository) {
        this.transactionService = transactionService;
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.paymentRepository = paymentRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @PostMapping("parent/admision/pay")
    public String pagarMatriculaNuevoEstudiante(@RequestParam("idStudent") String idStudent) {
        Student student = (Student) studentService.getStudentById(idStudent).get();
        PaymentType paymentType = paymentTypeRepository.getById(11);
        Payment payment = paymentRepository.findByPaymentTypeAndIsActiveIsTrue(paymentType);


        enrollmentService.updateStatusForNewStudent(student, 2);
//        transactionService.update(idStudent, payment);
        return "redirect:/parent";
    }
}
