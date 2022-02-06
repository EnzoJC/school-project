package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IGradeService {
    List<Grade> getAllGradesByLevel(Level level);

    Optional<Grade>  findById(int id);

    Set<Grade> getAll();

    void deleteById(int id);

    Grade update(Grade grade, int id);
}
