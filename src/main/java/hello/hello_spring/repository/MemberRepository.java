package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원이 저장소에 저장
    Optional<Member> findById(Long id); // 찾아옴
    Optional<Member> findByName(String name); // 찾아옴
    List<Member> findAll(); // 지금까지 저장된 모든 회원리스트를 반환해줌
}