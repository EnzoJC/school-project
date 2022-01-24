package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.TypeBirth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeBirthRepository extends JpaRepository<TypeBirth, Integer> {
}