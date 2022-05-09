package com.ljm.spring_boot.jwt.service;

import com.ljm.spring_boot.jwt.domain.Users;
import com.ljm.spring_boot.jwt.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findOneWithAuthoritiesByUsername(username).map(users -> createUser(username, users)).orElseThrow(()->new UsernameNotFoundException(username + " 찾을수 없는 유저 입니다."));
    }

    private User createUser(String username, Users users) {
        if( !users.isActivated()) {
            throw new RuntimeException(username + " 활성화 되지 않은 유저 입니다.");
        }
        List<GrantedAuthority> grantedAuthorities = users.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName())).collect(Collectors.toList());
        return new User(users.getUsername(), users.getPassword(), grantedAuthorities);
    }
}
