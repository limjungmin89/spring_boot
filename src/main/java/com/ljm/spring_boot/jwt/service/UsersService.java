package com.ljm.spring_boot.jwt.service;

import com.ljm.spring_boot.domain.Authority;
import com.ljm.spring_boot.jwt.domain.Users;
import com.ljm.spring_boot.jwt.dto.UsersDTO;
import com.ljm.spring_boot.jwt.repository.UsersRepository;
import com.ljm.spring_boot.jwt.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Users signup(UsersDTO userDto) {
        if (usersRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Users user = Users.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return usersRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<Users> getUserWithAuthorities(String username) {
        return usersRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Users> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(usersRepository::findOneWithAuthoritiesByUsername);
    }
}
