package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRelationshipRepository extends JpaRepository<Relationship, Integer> {
}
