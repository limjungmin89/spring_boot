package com.ljm.spring_boot.repository;

import com.ljm.spring_boot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();

        member.setName("limjungmin");
        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("member1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("member1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("member1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
