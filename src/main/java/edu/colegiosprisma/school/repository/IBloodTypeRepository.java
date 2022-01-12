package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloodTypeRepository extends JpaRepository<BloodType, Integer> {
}