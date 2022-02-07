package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.Relationship;
import edu.colegiosprisma.school.service.IRelationshipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class RelationshipController {
    private final IRelationshipService relationshipService;

    public RelationshipController(IRelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @GetMapping("/relationship")
    public @ResponseBody List<Relationship> getRelationship() {
        return relationshipService.getAll();
    }
}
