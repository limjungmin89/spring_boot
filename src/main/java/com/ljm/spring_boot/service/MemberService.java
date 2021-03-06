package com.ljm.spring_boot.service;

import com.ljm.spring_boot.domain.Member;
import com.ljm.spring_boot.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {

        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {

        List<Member> members = memberRepository.findAll();

        return members;
    }

    /**
     * id로 조회
     * @param id
     * @return
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
