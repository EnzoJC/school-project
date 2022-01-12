package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.*;
import edu.colegiosprisma.school.repository.*;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

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
    public Enrollment create(Enrollment enrollment, Student student) {
        LocalDate date = LocalDate.now();
        int currentYear = date.getYear();
        SchoolYear schoolYear = schoolYearRepository.findByYear(currentYear);

        Optional<State> state = stateRepository.findById(1); // 1: Pendiente de pago

        enrollment.setSchoolYear(schoolYear);
        enrollment.setStudent(student);
        enrollment.setState(state.isPresent() ? state.get() : new State());
        enrollment.setCurrentYear(true);

        PaymentType paymentType = paymentTypeRepository.findById(11).isPresent() ? paymentTypeRepository.findById(11).get() : new PaymentType(); // 11: Matricula
        Payment payment = paymentRepository.findByPaymentTypeAndIsActiveIsTrue(paymentType);
        enrollmentRepository.save(enrollment);
        transactionService.create(enrollment, Set.of(payment));

        return enrollment;
    }

    @Override
    public Enrollment updateStatusForNewStudent(Student student, int status) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        Optional<State> state = stateRepository.findById(status); // 2: Pagado

        enrollment.setState(state.isPresent() ? state.get() : new State());
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment updateStatusForOldStudent(Student student, int status) {
        Enrollment enrollment = enrollmentRepository.findByStudentAndCurrentYearIsTrue(student);
        Optional<State> state = stateRepository.findById(status); // 2: Pagado

        enrollment.setState(state.isPresent() ? state.get() : new State());
        enrollmentRepository.save(enrollment);
        return enrollment;
    }
}
