package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.repository.INationalityRepository;
import edu.colegiosprisma.school.service.INationalityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalityServImpl implements INationalityService {
    private final INationalityRepository nationalityRepository;

    public NationalityServImpl(INationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    public List<Nationality> getAll() {
        return nationalityRepository.findAll();
    }
}
