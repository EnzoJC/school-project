package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.service.IDebtService;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DebtController {

    private final IDebtService debtService;
    private final IEnrollmentService enrollmentService;
    @Autowired
    private IStudentService studentService;

    public DebtController(IDebtService debtService, IEnrollmentService enrollmentService) {
        this.debtService = debtService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("parent/admision/pay")
    public String pagarMatricula(@RequestParam("idStudent") String idStudent,
                        @RequestParam(value = "idPayment", required = false, defaultValue = "11") String idPayment) {
        Student student = (Student) studentService.getStudentById(idStudent).get();
        enrollmentService.updatePayment(student);
        debtService.update(idStudent, idPayment);
        return "redirect:/parent";
    }
}
