package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGradeRepository extends JpaRepository<Grade, Integer> {
    // findAllByLevel: busca todos los grados por nivel
    List<Grade> findAllByLevel(Level level);
}