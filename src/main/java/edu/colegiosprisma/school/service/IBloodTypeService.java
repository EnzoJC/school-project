package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.BloodType;
import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;

public interface IBloodTypeService {
    List<BloodType> getAll();

    Optional<BloodType>  findById(int id);

    void deleteById(int id);

    BloodType update(BloodType bloodType, int id);
}
