package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.TypeOfBirth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeOfBirthRepository extends JpaRepository<TypeOfBirth, Integer> {
}