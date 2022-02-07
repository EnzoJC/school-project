package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.entity.State;
import edu.colegiosprisma.school.entity.TypeBirth;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDistrictService {
    List<District> getAllDistrictsByProvince(Province provinceId);

    Optional<District>  findById(String id);

    Set<District> getAll();

    void deleteById(String id);

    District update(District district, String id);
}
