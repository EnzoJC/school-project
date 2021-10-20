package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILevelRepository extends JpaRepository<Level, Integer> {
}