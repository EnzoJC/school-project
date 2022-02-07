package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.service.ILevelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api")
public class LevelController {
    private final ILevelService levelService;

    public LevelController(ILevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/level")
    public @ResponseBody List<Level> getLevels() {
        return levelService.getAll();
    }
}
