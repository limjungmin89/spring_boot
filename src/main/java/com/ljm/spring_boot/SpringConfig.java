package com.ljm.spring_boot;

import com.ljm.spring_boot.repository.MemberRepository;
import com.ljm.spring_boot.repository.MemoryMemberRepository;
import com.ljm.spring_boot.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
