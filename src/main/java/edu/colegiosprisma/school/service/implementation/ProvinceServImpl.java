package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Department;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.repository.IProvinceRepository;
import edu.colegiosprisma.school.service.IProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServImpl implements IProvinceService {
    private final IProvinceRepository provinceRepository;

    public ProvinceServImpl(IProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> getAllProvincesByDepartment(Department departament) {
        return provinceRepository.getAllProvinceByDepartment(departament);
    }

    @Override
    public Province findById(String id) {
        return provinceRepository.findById(id).isPresent() ? provinceRepository.findById(id).get() : new Province();
    }

    @Override
    public void deleteById(String id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public Province update(Province province, String id) {
        return null;
    }
}
