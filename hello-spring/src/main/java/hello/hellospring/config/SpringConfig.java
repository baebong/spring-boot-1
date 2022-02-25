package hello.hellospring.config;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /*
    private DataSource dataSource; // 순수 JDBC

    @Autowired // 순수 JDBC
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    /*
    private EntityManager em; // JPA

    @Autowired // JPA
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    private final MemberRepository memberRepository; // 스프링 데이터 JPA

    @Autowired // 생성자가 하나일 경우 생략가능
    public SpringConfig(MemberRepository memberRepository) { // 스프링 데이터 JPA
        this.memberRepository = memberRepository;
    }
    
    /*
    @Bean
    public MemberRepository memberRepository() { // 스프링 데이터 JPA에선 필요없음
        // return new MemoryMemberRepository(); // 구현체로 인스턴스 생성
        // return new JdbcMemberRepository(dataSource); // 순수 JDBC
        // return new JdbcTemplateMemberRepository(dataSource); // 스프링 JdbcTemplate
        return new JpaMemberRepository(em); // JPA 방식 데이터 관리
    }
    */

    @Bean
    public MemberService memberService() {
        // return new MemberService(memberRepository()); // service를 구현하기 위해서는 repository가 필요
        return new MemberService(memberRepository); // 스프링 데이터 JPA
    }
}
