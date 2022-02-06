package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Occupation;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IOccupationService {
    List<Occupation> getAll();

    Optional<Occupation>  findById(int id);

    void deleteById(int id);

    Occupation update(Occupation occupation, int id);
}
