package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.repository.IGenderRepository;
import edu.colegiosprisma.school.service.IGenderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Gender> findById(int id) {
        return genderRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        genderRepository.deleteById(id);
    }

    @Override
    public Gender update(Gender gender, int id) {
        return null;
    }
}
