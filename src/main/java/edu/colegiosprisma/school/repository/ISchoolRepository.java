package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolRepository extends JpaRepository<School, String> {
}