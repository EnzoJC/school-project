package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.DocumentType;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IDocumentTypeService {
    // Traer todos los tipos de documentos de identidad
    List<DocumentType> getAllDocumentTypes();
}
