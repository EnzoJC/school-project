package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Occupation;
import edu.colegiosprisma.school.repository.IOccupationRepository;
import edu.colegiosprisma.school.service.IOccupationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupationServImpl implements IOccupationService {
    private final IOccupationRepository occupationRepository;

    public OccupationServImpl(IOccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    @Override
    public List<Occupation> getAll() {
        return occupationRepository.findAll();
    }
}
