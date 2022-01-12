package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProvinceRepository extends JpaRepository<Province, String> {
    List<Province> getAllProvinceByDepartment(Department department);
}