package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.service.IGenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class GenderController {
    private final IGenderService genderService;

    public GenderController(IGenderService genderService) {
        this.genderService = genderService;
    }
    @GetMapping("/gender")
    public @ResponseBody List<Gender> getAllGenders() {
        return genderService.getAll();
    }
}
