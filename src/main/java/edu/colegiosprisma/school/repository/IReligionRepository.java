package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Religion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReligionRepository extends JpaRepository<Religion, Integer> {
}