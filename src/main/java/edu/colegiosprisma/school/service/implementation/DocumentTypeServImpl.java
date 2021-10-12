package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.repository.IDocumentTypeRepository;
import edu.colegiosprisma.school.service.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServImpl implements IDocumentTypeService {
    @Autowired
    private IDocumentTypeRepository documentTypeRepository;
    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }
}
