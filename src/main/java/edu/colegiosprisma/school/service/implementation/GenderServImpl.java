package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Gender;
import edu.colegiosprisma.school.repository.IGenderRepository;
import edu.colegiosprisma.school.service.IGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServImpl implements IGenderService {

    @Autowired
    private IGenderRepository genderRepository;

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }
}
