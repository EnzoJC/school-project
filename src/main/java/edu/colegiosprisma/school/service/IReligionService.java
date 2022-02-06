package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Religion;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IReligionService {
    List<Religion> getAll();

    Optional<Religion>  findById(int id);

    void deleteById(int id);

    Religion update(Religion religion, int id);
}
