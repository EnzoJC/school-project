package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Level;
import edu.colegiosprisma.school.repository.ILevelRepository;
import edu.colegiosprisma.school.service.ILevelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServImpl implements ILevelService {
    private final ILevelRepository levelRepository;

    public LevelServImpl(ILevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> findLevelById(Integer id) {
        return levelRepository.findById(id);
    }
}
