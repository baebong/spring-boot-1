package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service // 컴포넌트 스캔 방식
@Transactional // JPA 사용하기 위함
public class MemberService { // Service 부분은 비즈니스 로직을 다루기 때문에  메소드 이름도 비즈니스적이어야 함

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 경우 회원X
        // Optional<Member> result = memberRepository.findByName(member.getName()); // ctrl + alt + v : 자동으로 return 값 생성
        /*
        // validateDuplicateMember(member)로 로직을 묶음
        memberRepository.findByName(member.getName()) // 어차피 Optional로 반환될 것이기 때문에 코드를 이을 수 있다.
                .ifPresent(m -> { // m값 존재 > true 반환 > "이미 존재하는 회원입니다." 출력
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                  });
         */
        // 중복 회원 검증 메소드
        validateDuplicateMember(member); // ctrl + alt + m : 특정 로직을 메소드로 묶어줌
        // ctrl + alt + shift + t : Refactor this

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 어차피 Optional로 반환될 것이기 때문에 코드를 이을 수 있다.
                .ifPresent(m -> { // m값 존재 > true 반환 > "이미 존재하는 회원입니다." 출력
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 선택 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
