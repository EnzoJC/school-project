package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findAllByLevel(Level level);
}