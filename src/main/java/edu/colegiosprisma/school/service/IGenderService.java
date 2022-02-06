package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IGenderService {
    List<Gender> getAll();

    Optional<Gender>  findById(int id);

    void deleteById(int id);

    Gender update(Gender gender, int id);
}
