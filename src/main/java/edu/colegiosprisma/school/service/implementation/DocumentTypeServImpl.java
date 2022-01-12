package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.repository.IDocumentTypeRepository;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServImpl implements IDocumentTypeService {
    private final IDocumentTypeRepository documentTypeRepository;

    public DocumentTypeServImpl(IDocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public List<DocumentType> getAll() {
        return documentTypeRepository.findAll();
    }
}
