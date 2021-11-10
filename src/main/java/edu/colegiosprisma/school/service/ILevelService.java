package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Level;

import java.util.List;
import java.util.Optional;

public interface ILevelService {
    // getAllLevels permite obtener todos los niveles
    List<Level> getAllLevels();
    // getLevel permite obtener un nivel por su id
    Optional<Level> getLevel(Integer id);
}
