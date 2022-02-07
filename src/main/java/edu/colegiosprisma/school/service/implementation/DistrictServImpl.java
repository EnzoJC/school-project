package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.District;
import edu.colegiosprisma.school.entity.Province;
import edu.colegiosprisma.school.repository.IDistrictRepository;
import edu.colegiosprisma.school.service.IDistrictService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<District> findById(String id) {
        return districtRepository.findById(id);
    }

    @Override
    public Set<District> getAll() {
        return new HashSet<>(districtRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        districtRepository.deleteById(id);
    }

    @Override
    public District update(District district, String id) {
        return null;
    }
}
