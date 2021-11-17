package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.TypeDisability;
import edu.colegiosprisma.school.repository.ITypeDisabilityRepository;
import edu.colegiosprisma.school.service.ITypeDisabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDisabilityServImpl implements ITypeDisabilityService {
    @Autowired
    private ITypeDisabilityRepository typeDisabilityRepository;

    @Override
    public List<TypeDisability> getAllTypeOfDisabilities() {
        return typeDisabilityRepository.findAll();
    }
}
