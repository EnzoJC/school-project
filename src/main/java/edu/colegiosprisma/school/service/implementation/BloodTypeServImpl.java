package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.BloodType;
import edu.colegiosprisma.school.repository.IBloodTypeRepository;
import edu.colegiosprisma.school.service.IBloodTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodTypeServImpl implements IBloodTypeService {
    private final IBloodTypeRepository bloodTypeRepository;

    public BloodTypeServImpl(IBloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public List<BloodType> getAll() {
        return bloodTypeRepository.findAll();
    }

    @Override
    public Optional<BloodType> findById(int id) {
        return bloodTypeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        bloodTypeRepository.deleteById(id);
    }

    @Override
    public BloodType update(BloodType bloodType, int id) {
        return null;
    }
}
