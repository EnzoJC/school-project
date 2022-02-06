package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.entity.TypeDisability;
import edu.colegiosprisma.school.entity.User;

import java.util.Optional;
import java.util.Set;

public interface ITypeDisabilityService {
    Optional<TypeDisability> getById(int id);
    Set<TypeDisability> getAll();
    void deleteById(int id);
    TypeDisability update(TypeDisability typeDisability, int id);
}
