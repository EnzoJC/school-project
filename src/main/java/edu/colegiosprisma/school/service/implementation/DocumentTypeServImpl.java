package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.repository.IDocumentTypeRepository;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<DocumentType> findById(int id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        documentTypeRepository.deleteById(id);
    }

    @Override
    public DocumentType update(DocumentType documentType, int id) {
        return null;
    }
}
