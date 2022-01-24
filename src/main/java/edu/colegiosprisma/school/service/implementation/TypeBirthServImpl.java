package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.TypeBirth;
import edu.colegiosprisma.school.repository.ITypeBirthRepository;
import edu.colegiosprisma.school.service.IPaymentService;
import edu.colegiosprisma.school.service.ITypeBirthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TypeBirthServImpl implements ITypeBirthService {
    private final ITypeBirthRepository typeBirthRepository;

    public TypeBirthServImpl(ITypeBirthRepository typeBirthRepository) {
        this.typeBirthRepository = typeBirthRepository;
    }

    @Override
    public Set<TypeBirth> getAll() {
        return new LinkedHashSet<>(typeBirthRepository.findAll());
    }
}
