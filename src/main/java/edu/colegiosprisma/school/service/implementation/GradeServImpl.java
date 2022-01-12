package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.repository.IGradeRepository;
import edu.colegiosprisma.school.service.IGradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServImpl implements IGradeService {
    private final IGradeRepository gradeRepository;

    public GradeServImpl(IGradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGradesByLevel(Level level) {
        return gradeRepository.findAllByLevel(level);
    }

}
