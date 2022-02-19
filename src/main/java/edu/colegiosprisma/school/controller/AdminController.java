package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Enrollment;
import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.Student;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.service.IEnrollmentService;
import edu.colegiosprisma.school.service.IParentService;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private IUserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/admin";
    }

    @GetMapping("/admin/estudiantes")
    public String estudiantes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/students";
    }


    @Autowired
    public IParentService parentService;

    @GetMapping("/admin/padres")
    public String parents(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames())
                .addAttribute("listaPadres", parentService.getAll())
                .addAttribute("padre",new Parent());
        return "/admin/parents";
    }

    @PostMapping("/admin/padres")
    public String agregarpadre(Parent parent) {
        parentService.create(parent);
        return "redirect:/admin/padres";
    }
    @PostMapping("/admin/padres/editar")
    public String editarpadre(Parent parent) {
        parentService.update(parent, parent.getId());
        return "redirect:/admin/padres";
    }

    @GetMapping("/admin/padres/eliminar/{id}")
    public String eliminarpadre(@PathVariable(value = "id") String id) {
        parentService.deleteById(id);
        return "redirect:/admin/padres";
    }

    @Autowired
    public IEnrollmentService enrollmentService;

    @GetMapping("/admin/matriculas")
    public String matriculas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/enrollments";
    }

    @GetMapping("/admin/matriculas/eliminar/{id}")
    public String eliminarpadre(@PathVariable(value = "id") Integer id) {
        enrollmentService.deleteById(id);
        return "redirect:/admin/matriculas";
    }

    @GetMapping("/admin/anios")
    public String anios(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/scholaryear";
    }



    @GetMapping("/admin/transacciones")
    public String transacciones(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/transaction";
    }
    @GetMapping("/admin/pagos")
    public String pagos(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/payments";
    }
    @GetMapping("/admin/deudas")
    public String deudas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/debts";
    }
    @GetMapping("/admin/tiposPago")
    public String tiposPago(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("nombresCompletos", user.getGivenNames());
        return "/admin/paymentTypes";
    }
    @GetMapping("/admin/admin-profile")
    public String updateProfile(Model model) {
        User admin = getCurrentUser();
        model.addAttribute("admin", admin);
        model.addAttribute("nombresCompletos", admin.getGivenNames());
        return "admin/admin-profile";
    }

    @PostMapping("/admin/admin-profile")
    public String updateProfile(@ModelAttribute("admin") User admin) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String id = userDetails.getUsername();
        userService.update(admin, id);
        return "redirect:/admin/admin-profile";
    }

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByUsername(userDetails.getUsername());
    }
}
