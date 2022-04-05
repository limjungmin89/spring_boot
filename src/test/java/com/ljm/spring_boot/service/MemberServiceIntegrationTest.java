package com.ljm.spring_boot.service;

import com.ljm.spring_boot.domain.Member;
import com.ljm.spring_boot.repository.MemberRepository;
import com.ljm.spring_boot.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("limjungmin");

        // when
        Long id = memberService.join(member);

        // then
        Optional<Member> findMember = memberService.findOne(id);
        Assertions.assertEquals(member.getName(),findMember.get().getName());
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("limjungmin");

        Member member2 = new Member();
        member2.setName("limjungmin");

        memberService.join(member1);
//        try {
//            memberService.join(member2);
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findOne() {
    }
}
