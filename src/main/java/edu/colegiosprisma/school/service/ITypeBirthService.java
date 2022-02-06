package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.TypeBirth;
import edu.colegiosprisma.school.entity.TypeDisability;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITypeBirthService {

    Optional<TypeBirth> getById(int id);
    Set<TypeBirth> getAll();
    void deleteById(int id);
    TypeBirth update(TypeBirth typeBirth, int id);
}
