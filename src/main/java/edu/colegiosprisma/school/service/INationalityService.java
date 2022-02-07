package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface INationalityService {
    List<Nationality> getAll();

    Optional<Nationality>  findById(int id);

    void deleteById(int id);

    Nationality update(Nationality nationality, int id);
}
