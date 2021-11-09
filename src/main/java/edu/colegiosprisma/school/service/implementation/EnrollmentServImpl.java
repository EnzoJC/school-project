package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.*;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IPaymentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServImpl implements IEnrollmentService {
    private final IEnrollmentRepository enrollmentRepository;
    private final ISchoolYearRepository schoolYearRepository;
    private final IStateRepository stateRepository;
    private final ITransactionService transactionService;
    private final IPaymentRepository paymentRepository;
    private final IPaymentTypeRepository paymentTypeRepository;

    @Autowired
    public EnrollmentServImpl(IEnrollmentRepository enrollmentRepository, ISchoolYearRepository schoolYearRepository,
                              IStateRepository stateRepository, ITransactionService transactionService,
                              IPaymentRepository paymentRepository, IPaymentTypeRepository paymentTypeRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.stateRepository = stateRepository;
        this.transactionService = transactionService;
        this.paymentRepository = paymentRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment, Student student) {
        LocalDate date = LocalDate.now();
        int currentYear = date.getYear();
        SchoolYear schoolYear = schoolYearRepository.findByYear(currentYear);

        Optional<State> state = stateRepository.findById(1); // 1: Pendiente de pago

        enrollment.setSchoolYear(schoolYear);
        enrollment.setStudent(student);
        enrollment.setState(state.get());

        PaymentType paymentType = paymentTypeRepository.findById(11).get(); // 11: Matricula
        System.out.println("paymentType: " + paymentType.getName());
        Payment payment = paymentRepository.findByPaymentTypeAndIsActiveIsTrue(paymentType);
        System.out.println("Payment2: " + payment.getDescription());
        enrollmentRepository.save(enrollment);
        transactionService.createTransaction(enrollment, List.of(payment));

        return enrollment;
    }

    @Override
    public Enrollment updateStatusForNewStudent(Student student, int status) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        Optional<State> state = stateRepository.findById(status); // 2: Pagado

        enrollment.setState(state.get());
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment updateStatusForOldStudent(Student student, int status) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        Optional<State> state = stateRepository.findById(status); // 2: Pagado

        enrollment.setState(state.get());
        enrollmentRepository.save(enrollment);
        return enrollment;
    }
}
