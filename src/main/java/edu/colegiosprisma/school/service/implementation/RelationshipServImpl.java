package edu.colegiosprisma.school.service.implementation;


import edu.colegiosprisma.school.entity.DocumentType;
import edu.colegiosprisma.school.entity.Relationship;
import edu.colegiosprisma.school.repository.IDocumentTypeRepository;
import edu.colegiosprisma.school.repository.IRelationshipRepository;
import edu.colegiosprisma.school.service.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServImpl implements IRelationshipService {

    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Override
    public List<Relationship> getAllRelationships() {
        return relationshipRepository.findAll();
    }
}
