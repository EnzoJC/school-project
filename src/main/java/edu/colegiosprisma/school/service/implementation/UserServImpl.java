package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Role;
import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IUserRepository;
import edu.colegiosprisma.school.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Qualifier("IUserRepository")
    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("IUserRepository")
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public User findByUsername(String user) {
        return iUserRepository.findByUsername(user);
    }

    @Override
    // @Transactional es para que no se ejecute dos veces la transacción
    // Una transacción es una secuencia de acciones que se ejecutan en una base de datos.
    @Transactional(readOnly = true) // @Transactional(readOnly = true) es para que no se guarde en la base de datos.
    // loadUserByUsername(): es un método que se ejecuta cuando se llama al método de autenticación.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario por el username y lo guardamos en una variable
        User user = userRepository.findByUsername(username);
        // Si el usuario no existe, se lanza una excepción
        if (user == null) {
            throw new UsernameNotFoundException("No se pudo encontrar el usuario: " + username);
        }
        // Se crea una lista de roles tipo GrantedAuthority como Set
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        // Se crea una instancia de UserDetails y se le asigna el usuario y la lista de roles
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
