package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 회원을 저장할 하나의 데이터베이스라고 생각하면 됨.

    private static long sequence = 0L; //일련 번호

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버를 저장할 때 일련번호 값을 1 증가 시켜주기. id setting
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //만약 null을 반환할 경우를 대비해서 Optional을 사용해서 감싸준다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //같은 name을 가지고 있는 객체를 찾으면 반환. 없으면 null반환
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
