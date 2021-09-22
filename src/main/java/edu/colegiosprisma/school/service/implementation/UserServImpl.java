package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IUserRepository;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("IUserServImpl")
public class UserServImpl implements IUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("IUserRepository")
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public User findByUsername(String user) {
        return iUserRepository.findByUsername(user);
    }
}
