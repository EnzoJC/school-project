package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Level;

import java.util.List;
import java.util.Optional;

public interface ILevelService {
    List<Level> getAllLevels();
    Optional<Level> getLevel(Integer id);
}
