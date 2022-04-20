package com.ljm.spring_boot.repository;

import com.ljm.spring_boot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    Optional<Member> findByName(String name);

}
