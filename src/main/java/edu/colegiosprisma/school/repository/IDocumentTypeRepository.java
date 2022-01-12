package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

// LA CARPETA REPOSITY EN LA CARPETA EN LA CUAL DEFINIDEROS TODOS LO RELACIONADO A LOS DATOS
public interface IDocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
}
