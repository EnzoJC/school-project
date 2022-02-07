package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.service.IGradeService;
import edu.colegiosprisma.school.service.ILevelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class GradeController {
    private final IGradeService gradeService;
    private final ILevelService levelService;

    public GradeController(IGradeService gradeService, ILevelService levelService) {
        this.gradeService = gradeService;
        this.levelService = levelService;
    }

    @GetMapping("/grade")
    public @ResponseBody List<Grade> getGrades(@RequestParam(value = "level") Integer id) {
        Optional<Level> level = levelService.findLevelById(id);
        return level.isPresent() ? gradeService.getAllGradesByLevel(level.get()) : new ArrayList<>();
    }
}
