package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolYearRepository extends JpaRepository<SchoolYear, Integer> {

    SchoolYear findByYear(Integer year);
}