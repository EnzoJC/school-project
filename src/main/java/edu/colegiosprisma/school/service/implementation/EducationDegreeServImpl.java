package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.EducationDegree;
import edu.colegiosprisma.school.repository.IEducationDegreeRepository;
import edu.colegiosprisma.school.service.IEducationDegreeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeServImpl implements IEducationDegreeService {
    private final IEducationDegreeRepository educationDegreeRepository;

    public EducationDegreeServImpl(IEducationDegreeRepository educationDegreeRepository) {
        this.educationDegreeRepository = educationDegreeRepository;
    }

    @Override
    public List<EducationDegree> getAll() {
        return educationDegreeRepository.findAll();
    }
}
