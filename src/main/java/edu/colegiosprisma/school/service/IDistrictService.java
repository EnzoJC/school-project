package edu.colegiosprisma.school.service;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;

import java.util.List;

public interface IDistrictService {
    List<District> getAllDistrictsByProvince(Province provinceId);
}
