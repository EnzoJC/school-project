package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOccupationRepository extends JpaRepository<Occupation, Integer> {
}