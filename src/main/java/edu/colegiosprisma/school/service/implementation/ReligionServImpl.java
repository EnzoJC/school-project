package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Religion;
import edu.colegiosprisma.school.repository.IReligionRepository;
import edu.colegiosprisma.school.service.IReligionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReligionServImpl implements IReligionService {
    @Autowired
    private IReligionRepository religionRepository;
    @Override
    public List<Religion> getAllReligions() {
        return religionRepository.findAll();
    }
}
