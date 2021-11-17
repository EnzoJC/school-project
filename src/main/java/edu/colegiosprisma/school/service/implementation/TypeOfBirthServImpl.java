package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.TypeOfBirth;
import edu.colegiosprisma.school.repository.ITypeOfBirthRepository;
import edu.colegiosprisma.school.service.ITypeOfBirthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfBirthServImpl implements ITypeOfBirthService {
    @Autowired
    private ITypeOfBirthRepository typeOfBirthRepository;
    @Override
    public List<TypeOfBirth> getAllTypeOfBirths() {
        return typeOfBirthRepository.findAll();
    }
}
