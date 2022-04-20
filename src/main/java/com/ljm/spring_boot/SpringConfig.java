package com.ljm.spring_boot;

import com.ljm.spring_boot.repository.*;
import com.ljm.spring_boot.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager entityManager;

    private final DataSource dataSource;

    public SpringConfig(EntityManager entityManager, DataSource dataSource) {
        this.entityManager = entityManager;
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcRepository(dataSource);
        //return new JdbcTemplateRepository(dataSource);
        return new JpaRepository(entityManager);
    }
}
