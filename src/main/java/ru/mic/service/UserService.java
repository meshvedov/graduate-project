package ru.mic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mic.model.Role;
import ru.mic.model.User;
import ru.mic.repository.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);

        if (user == null) {
            throw new RuntimeException("OOPS! User not found ");
        }

        String password = "{bcrypt}" + encoder.encode(user.getPassword());
        return new org.springframework.security.core.userdetails.User(user.getName(), password, getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));

        return authorities;
    }
}
