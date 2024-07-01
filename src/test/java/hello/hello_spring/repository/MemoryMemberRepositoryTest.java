package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // save, findByName 같은 메서드들이 끝나고 호출됨
    public void afterEach() {
        repository.clearStore();
// @AfterEach 어노테이션을 사용하면 하나의 테스트가 끝날 때마다 실행하는 콜백 메소드를 지정할 수 있다.
        // 테스트가 실행되고 끝날때마다 한번씩 저장소를 지움
        // 테스트는 서로 순서와 관계없이 의존관계가 없이 설계되야함 즉, 하나의 테스트가 끝날
    //   때마다 공유데이터들을 지워줘야함 -> 그래야 문제가 없음
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring"); //이름 설정
        //when
        repository.save(member); //멤버 저장
        //then
        Member result = repository.findById(member.getId()).get(); //저장되었는지 아이디로 멤버를 찾기
        //Assertions.assertEquals(member, result); //[검증 1] 두개의 객체가 동일한지
        assertThat(member).isEqualTo(result); //[검증 2] 두 객체가 동일한지 확인
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get(); //spring1 이라는 이름으로 객체 찾기
        //then
        assertThat(result).isEqualTo(member1); //위에서 생성한 객체와 동일한 객체인지 확인
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll(); //전체 목록 가져오기

        //then
        assertThat(result.size()).isEqualTo(2); //전체 목록의 개수가 총 2개인지 확인
    }
}
