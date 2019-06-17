package hu.kalee.multi.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        final String username1 = user.getUsername();
        final String password = user.getPassword();
        /*
        final Collection<? extends GrantedAuthority> authorities = user.getPrivileges().stream()
            .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
            .collect(Collectors.toSet());
        */

        return new User(username1, password, Collections.emptyList());
    }
}