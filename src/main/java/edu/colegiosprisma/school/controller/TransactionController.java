package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStateService;
import edu.colegiosprisma.school.service.IStudentService;
import edu.colegiosprisma.school.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IEnrollmentService enrollmentService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IStateService stateService;

    @PostMapping("parent/admision/pay")
    public String pagarMatriculaNuevoEstudiante(@RequestParam("idStudent") String idStudent,@RequestParam("description") String description) {
        if (studentService.findById(idStudent).isPresent()) {
            Student student = (Student) studentService.findById(idStudent).get();
            State state = stateService.findById(5); // 5: Pendiente de pago
            if (transactionService.pay(student, state,description))
                enrollmentService.updateStatusForNewStudent(student, 2); // 2: Pre-inscrito
        }
        return "redirect:/parent/applicants-list";
    }
}
