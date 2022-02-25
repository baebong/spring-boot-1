package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id); // Optional<> : 자바8부터 들어간 기능, null을 처리하는 방법 중 하나로 쓰임

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
