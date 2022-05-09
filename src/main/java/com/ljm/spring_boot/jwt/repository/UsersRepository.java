package com.ljm.spring_boot.jwt.repository;

import com.ljm.spring_boot.jwt.domain.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByUsername(String username);
}
