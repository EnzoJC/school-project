package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.repository.IGenderRepository;
import edu.colegiosprisma.school.service.IGenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServImpl implements IGenderService {
    private final IGenderRepository genderRepository;

    public GenderServImpl(IGenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> getAll() {
        return genderRepository.findAll();
    }
}
