package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.service.IDebtService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DebtController {

    private final IDebtService debtService;

    public DebtController(IDebtService debtService) {
        this.debtService = debtService;
    }

    @PostMapping("parent/admision/pay")
    public String pagarMatricula(@RequestParam("idStudent") String idStudent,
                        @RequestParam(value = "idPayment", required = false, defaultValue = "11") String idPayment) {
        System.out.println("Hola: " + idStudent);
        return "redirect:/parent";
    }
}
