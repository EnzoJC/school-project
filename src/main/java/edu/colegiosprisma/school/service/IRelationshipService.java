package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.entity.Relationship;

import java.util.List;

public interface IRelationshipService {
    List<Relationship> getAllRelationships();
}
