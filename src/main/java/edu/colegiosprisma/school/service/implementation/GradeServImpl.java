package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.repository.IGradeRepository;
import edu.colegiosprisma.school.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServImpl implements IGradeService {

    @Autowired
    private IGradeRepository gradeRepository;

    @Override
    public List<Grade> getAllGradesByLevel(Level level) {
        return gradeRepository.findAllByLevel(level);
    }
}
