package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.repository.IProvinceRepository;
import edu.colegiosprisma.school.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServImpl implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;
    @Override
    public List<Province> getAllProvincesByDepartament(Department departament) {
        return provinceRepository.getAllProvinceByDepartment(departament);
    }

    @Override
    public Province getProvince(String id) {
        return provinceRepository.findById(id).get();
    }
}
