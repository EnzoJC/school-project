package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.EducationDegree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEducationDegreeRepository extends JpaRepository<EducationDegree, Integer> {
}