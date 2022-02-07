package edu.colegiosprisma.school.controller;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class DocumentTypeController {
    private final IDocumentTypeService documentTypeService;

    public DocumentTypeController(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping("/document-type")
    public @ResponseBody List<DocumentType> getDocumentTypes() {
        return documentTypeService.getAll();
    }
}
