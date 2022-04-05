package com.ljm.spring_boot;

import com.ljm.spring_boot.repository.JdbcRepository;
import com.ljm.spring_boot.repository.JdbcTemplateRepository;
import com.ljm.spring_boot.repository.MemberRepository;
import com.ljm.spring_boot.repository.MemoryMemberRepository;
import com.ljm.spring_boot.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
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
        return new JdbcTemplateRepository(dataSource);
    }
}
