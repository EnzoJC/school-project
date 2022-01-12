package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.repository.IDistrictRepository;
import edu.colegiosprisma.school.service.IDistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServImpl implements IDistrictService {
    private final IDistrictRepository districtRepository;

    public DistrictServImpl(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<District> getAllDistrictsByProvince(Province province) {
        return districtRepository.getAllDistrictsByProvince(province);
    }
}
