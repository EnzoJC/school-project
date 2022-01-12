package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.TypeDisability;
import edu.colegiosprisma.school.repository.ITypeDisabilityRepository;
import edu.colegiosprisma.school.service.ITypeDisabilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDisabilityServImpl implements ITypeDisabilityService {
    private final ITypeDisabilityRepository typeDisabilityRepository;

    public TypeDisabilityServImpl(ITypeDisabilityRepository typeDisabilityRepository) {
        this.typeDisabilityRepository = typeDisabilityRepository;
    }

    @Override
    public List<TypeDisability> getAll() {
        return typeDisabilityRepository.findAll();
    }
}
