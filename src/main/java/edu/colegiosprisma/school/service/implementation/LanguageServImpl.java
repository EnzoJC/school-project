package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Language;
import edu.colegiosprisma.school.repository.ILanguageRepository;
import edu.colegiosprisma.school.service.ILanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServImpl implements ILanguageService {
    private final ILanguageRepository languageRepository;

    public LanguageServImpl(ILanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Optional<Language> findById(int id) {
        return languageRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        languageRepository.deleteById(id);
    }

    @Override
    public Language update(Language language, int id) {
        return null;
    }
}
