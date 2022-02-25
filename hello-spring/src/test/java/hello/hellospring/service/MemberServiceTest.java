package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// shift + F10 : 이전에 실행한 것 Run
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // test 메소드 실행전 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 메서드가 실행되고 끝날 때마다 동작
    public void afterEach() {
        memberRepository.clearStore(); // 공용 데이터 삭제
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1); // 첫번째 아이디 spring 생성
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// member2를 넣으면 예외가 발생해야함
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2); // 두번째 아이디 spring 생성 시 오류 발생해야함 == 중복을 허용하지 않기 때문
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "이미 존재하는 회원입니다."); // 같은 오류값이 반환되면 테스트 성공
        }
        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}