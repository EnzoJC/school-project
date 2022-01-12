package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILevelRepository extends JpaRepository<Level, Integer> {
}