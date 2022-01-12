package edu.colegiosprisma.school.repository;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<District, String> {
    List<District> getAllDistrictsByProvince(Province province);
}