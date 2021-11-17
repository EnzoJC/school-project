package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILanguageRepository extends JpaRepository<Language, Integer> {
}