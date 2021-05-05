package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Tutor;
import edu.colegiosprisma.school.repository.ITutorRepository;
import edu.colegiosprisma.school.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ITutorServImpl implements ITutorService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ITutorRepository iTutorRepository;

    @Override
    public Tutor registrar(Tutor tutor) {
        return null;
    }
}
