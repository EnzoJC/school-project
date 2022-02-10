package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStateService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
    private final ITransactionService transactionService;
    private final IEnrollmentService enrollmentService;
    private final IStudentService studentService;
    private final IStateService stateService;

    public TransactionController(ITransactionService transactionService, IEnrollmentService enrollmentService,
                                 IStudentService studentService, IStateService stateService) {
        this.transactionService = transactionService;
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.stateService = stateService;
    }

    @PostMapping("parent/admision/pay")
    public String pagarMatriculaNuevoEstudiante(@RequestParam("idStudent") String idStudent) {
        if (studentService.findById(idStudent).isPresent()) {
            Student student = (Student) studentService.findById(idStudent).get();
            State state = stateService.findById(5); // 5: Pendiente de pago
            if (transactionService.pay(student, state))
                enrollmentService.updateStatusForNewStudent(student, 2); // 2: Pre-inscrito
        }
        return "redirect:/parent/applicants-list";
    }
}
