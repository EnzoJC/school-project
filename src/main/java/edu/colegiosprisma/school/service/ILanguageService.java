package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Language;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ILanguageService {
    List<Language> getAll();

    Optional<Language> findById(int id);

    void deleteById(int id);

    Language update(Language language, int id);
}
