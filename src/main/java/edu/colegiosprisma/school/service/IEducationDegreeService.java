package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.EducationDegree;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IEducationDegreeService {
    List<EducationDegree> getAll();

    Optional<EducationDegree>  findById(int id);

    void deleteById(int id);

    EducationDegree update(EducationDegree educationDegree, int id);
}
