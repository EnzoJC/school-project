package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Religion;
import edu.colegiosprisma.school.repository.IReligionRepository;
import edu.colegiosprisma.school.service.IReligionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReligionServImpl implements IReligionService {
    private final IReligionRepository religionRepository;

    public ReligionServImpl(IReligionRepository religionRepository) {
        this.religionRepository = religionRepository;
    }

    @Override
    public List<Religion> getAll() {
        return religionRepository.findAll();
    }
}
