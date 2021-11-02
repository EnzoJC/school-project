package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.service.IDebtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DebtController {

    private final IDebtService debtService;

    public DebtController(IDebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping("parent/admision/pay")
    public String pagar(@RequestParam("idStudent") String idStudent, Model model) {
        model.addAttribute("idStudent", idStudent);
        return "modalPay";
    }

    @PostMapping("parent/admision/pay")
    public String pagar(@RequestParam("idStudent") String idStudent, @RequestParam("idPayment") String idPayment) {

        return "redirect:/parent";
    }
}
