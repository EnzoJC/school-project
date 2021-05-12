package edu.colegiosprisma.school.security;

import edu.colegiosprisma.school.entity.Role;
//import edu.colegiosprisma.school.entity.User;
import edu.colegiosprisma.school.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServImpl implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.colegiosprisma.school.entity.User user = userRepository.findByUsername(username);
        UserBuilder builder =  null;

        if (user != null) {
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(user.getPassword());

//            List roleList = new ArrayList();
//            for (Role role: user.getRoles()) {
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//                roleList.add(grantedAuthority);
//            }
            builder.authorities("ROLE_USER");
        }
        else throw new UsernameNotFoundException("Usuario no encontrado");

        return builder.build();

    }
}
