package edu.colegiosprisma.school.security;

import edu.colegiosprisma.school.entity.Login;
import edu.colegiosprisma.school.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginDetailsServImpl implements UserDetailsService {
    @Autowired
    private ILoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = loginRepository.findByUser(s);
        UserBuilder builder =  null;

        if (login != null) {
            builder = User.withUsername(s);
            builder.disabled(false);
            builder.password(login.getPassword());
            builder.authorities(new SimpleGrantedAuthority(login.getRole().getName()));
        }
        else throw new UsernameNotFoundException("Usuario no encontrado");

        return builder.build();
    }
}
