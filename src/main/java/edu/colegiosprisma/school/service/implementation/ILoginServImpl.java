package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.repository.ILoginRepository;
import edu.colegiosprisma.school.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ILoginServImpl implements ILoginService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ILoginRepository iLoginRepository;

    @Override
    public Login findByUser(String user) {
        return iLoginRepository.findByUser(user);
    }
    @Override
    public Login registrar(Login login){
        login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
        return iLoginRepository.save(login);
    }
}
