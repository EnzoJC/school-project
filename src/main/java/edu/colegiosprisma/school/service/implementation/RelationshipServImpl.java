package edu.colegiosprisma.school.service.implementation;


import edu.colegiosprisma.school.entity.Relationship;
import edu.colegiosprisma.school.repository.IRelationshipRepository;
import edu.colegiosprisma.school.service.IRelationshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServImpl implements IRelationshipService {
    private final IRelationshipRepository relationshipRepository;

    public RelationshipServImpl(IRelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    @Override
    public List<Relationship> getAll() {
        return relationshipRepository.findAll();
    }
}
