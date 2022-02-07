package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Grade;
import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.repository.IGradeRepository;
import edu.colegiosprisma.school.service.IGradeService;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Grade> findById(int id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Set<Grade> getAll() {
        return new HashSet<>(gradeRepository.findAll());
    }

    @Override
    public void deleteById(int id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public Grade update(Grade grade, int id) {
        return null;
    }

}
