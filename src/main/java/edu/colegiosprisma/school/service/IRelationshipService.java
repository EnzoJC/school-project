package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Relationship;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IRelationshipService {
    List<Relationship> getAll();

    Optional<Relationship>  findById(int id);

    void deleteById(int id);

    Relationship update(Relationship relationship, int id);
}
