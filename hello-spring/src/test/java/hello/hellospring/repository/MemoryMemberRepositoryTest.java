package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // test는 순서에 상관없이 실행되어야함
    // test는 메서드 간의 의존관계없이 개별적으로 실행되어야함

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 실행되고 끝날 때마다 동작
    public void afterEach() {
        repository.clearStore(); // 공용 데이터 삭제
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        assertEquals(member, result); // alt + enter 누르면 Assertions를 static으로 import 할 수 있다.
        // member : hello.hellospring.domain.Member@7ee8290b
        // result : hello.hellospring.domain.Member@7ee8290b
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift +F6 단축키를 이용하면 같은 이름을 한꺼번에 변경가능
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertEquals(result, member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(result.size(), 2);
    }
}
