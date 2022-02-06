package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.repository.INationalityRepository;
import edu.colegiosprisma.school.service.INationalityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Nationality> findById(int id) {
        return nationalityRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        nationalityRepository.deleteById(id);
    }

    @Override
    public Nationality update(Nationality nationality, int id) {
        return null;
    }
}
