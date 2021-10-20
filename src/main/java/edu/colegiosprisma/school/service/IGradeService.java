package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;

import java.util.List;

public interface IGradeService {
    List<Grade> getAllGradesByLevel(Level level);
}
