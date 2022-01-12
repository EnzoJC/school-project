package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IUserRepository;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServImpl implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;

    public UserServImpl(@Qualifier("IUserRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No se pudo encontrar el usuario: " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
