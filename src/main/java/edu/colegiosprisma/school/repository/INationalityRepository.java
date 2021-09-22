package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INationalityRepository extends JpaRepository<Nationality, Integer> {
}
