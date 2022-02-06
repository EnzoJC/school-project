package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.entity.State;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ILevelService {
    List<Level> getAll();
    Optional<Level> findLevelById(Integer id);

    void deleteById(Integer id);

    Level update(Level level, Integer id);
}
