package edu.colegiosprisma.school.service.implementation;


import edu.colegiosprisma.school.entity.Relationship;
import edu.colegiosprisma.school.repository.IRelationshipRepository;
import edu.colegiosprisma.school.service.IRelationshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Relationship> findById(int id) {
        return relationshipRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        relationshipRepository.deleteById(id);
    }

    @Override
    public Relationship update(Relationship relationship, int id) {
        return null;
    }
}
