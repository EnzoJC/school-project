package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.TypeOfBirth;
import edu.colegiosprisma.school.repository.ITypeOfBirthRepository;
import edu.colegiosprisma.school.service.ITypeOfBirthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfBirthServImpl implements ITypeOfBirthService {
    private final ITypeOfBirthRepository typeOfBirthRepository;

    public TypeOfBirthServImpl(ITypeOfBirthRepository typeOfBirthRepository) {
        this.typeOfBirthRepository = typeOfBirthRepository;
    }

    @Override
    public List<TypeOfBirth> getAll() {
        return typeOfBirthRepository.findAll();
    }
}
