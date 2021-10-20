package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.repository.ILevelRepository;
import edu.colegiosprisma.school.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServImpl implements ILevelService {
    @Autowired
    private ILevelRepository levelRepository;
    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> getLevel(Integer id) {
        return levelRepository.findById(id);
    }
}
