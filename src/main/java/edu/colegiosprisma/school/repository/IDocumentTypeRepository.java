package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
}
