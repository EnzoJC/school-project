package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDocumentTypeService {
    List<DocumentType> getAll();

    Optional<DocumentType>  findById(int id);

    void deleteById(int id);

    DocumentType update(DocumentType documentType, int id);
}
