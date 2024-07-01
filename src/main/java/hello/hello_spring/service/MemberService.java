package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// @Service
//service 클래스에는 비지니스에 가까운 기능을 구현하기 때문에 비지니스와 관련된 용어를 사용
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//다음과 같이 외부에서 객체를 생성해서 넣어주는 것을 Denpendency Injection(DI)라고 함.
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *
     회원가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //값이 있는 경우(Optional이라서 가능)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     *
     전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) { return memberRepository.findById(memberId);}
}