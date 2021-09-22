package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Nationality;
import edu.colegiosprisma.school.repository.INationalityRepository;
import edu.colegiosprisma.school.service.INationalityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NationalityServImpl implements INationalityService {
    @Autowired
    private INationalityRepository nationalityRepository;

    @Override
    public List<Nationality> getAllNationalities() {
        return nationalityRepository.findAll();
    }
}
