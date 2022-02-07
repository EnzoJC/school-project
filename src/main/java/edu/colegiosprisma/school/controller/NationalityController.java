package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.service.INationalityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class NationalityController {
    private final INationalityService nationalityService;

    public NationalityController(INationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @GetMapping("/nationality")
    public @ResponseBody List<Nationality> getNationalities() {
        return nationalityService.getAll();
    }
}
