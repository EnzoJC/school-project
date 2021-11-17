package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;

import java.util.List;

public interface IProvinceService {
    List<Province> getAllProvincesByDepartament(Department departamentId);
    Province getProvince(String id);
}
