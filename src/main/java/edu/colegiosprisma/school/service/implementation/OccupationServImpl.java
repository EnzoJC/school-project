package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Occupation;
import edu.colegiosprisma.school.repository.IOccupationRepository;
import edu.colegiosprisma.school.service.IOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupationServImpl implements IOccupationService {
    @Autowired
    private IOccupationRepository occupationRepository;
    @Override
    public List<Occupation> getAllOccupations() {
        return occupationRepository.findAll();
    }
}
