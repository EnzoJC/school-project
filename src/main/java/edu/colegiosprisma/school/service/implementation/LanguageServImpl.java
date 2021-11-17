package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Language;
import edu.colegiosprisma.school.repository.ILanguageRepository;
import edu.colegiosprisma.school.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServImpl implements ILanguageService {
    @Autowired
    private ILanguageRepository languageRepository;
    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
