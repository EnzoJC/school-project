package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.BloodType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBloodTypeRepository extends JpaRepository<BloodType, Integer> {
}