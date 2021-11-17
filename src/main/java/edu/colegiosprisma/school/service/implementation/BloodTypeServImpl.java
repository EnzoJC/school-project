package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.BloodType;
import edu.colegiosprisma.school.repository.IBloodTypeRepository;
import edu.colegiosprisma.school.service.IBloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodTypeServImpl implements IBloodTypeService {
    @Autowired
    private IBloodTypeRepository bloodTypeRepository;
    @Override
    public List<BloodType> getAllBloodTypes() {
        return bloodTypeRepository.findAll();
    }
}
