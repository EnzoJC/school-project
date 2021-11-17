package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.EducationDegree;
import edu.colegiosprisma.school.repository.IEducationDegreeRepository;
import edu.colegiosprisma.school.service.IEducationDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeServImpl implements IEducationDegreeService {
    @Autowired
    private IEducationDegreeRepository educationDegreeRepository;
    @Override
    public List<EducationDegree> getAllEducationDegrees() {
        return educationDegreeRepository.findAll();
    }
}
