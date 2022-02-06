package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.entity.Role;

import java.util.List;
import java.util.Set;

public interface IProvinceService {
    List<Province> getAllProvincesByDepartment(Department departamentId);

    Province findById(String id);

    void deleteById(String id);

    Province update(Province province, String id);
}
