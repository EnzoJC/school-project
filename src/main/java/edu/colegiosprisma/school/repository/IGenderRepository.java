package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenderRepository extends JpaRepository<Gender, Integer> {
}
