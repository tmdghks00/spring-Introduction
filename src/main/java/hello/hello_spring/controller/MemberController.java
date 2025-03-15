package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Controller: 스프링 MVC 컨트롤러로 인식되며, 클라이언트의 요청을 처리함.
 */
@Controller
public class MemberController {
    private final MemberService memberService;

    /**
     * @Autowired: 생성자 주입 방식으로 MemberService를 주입받음.
     * MemberController가 생성될 때, Spring이 MemberService를 자동으로 주입해줌.
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입 폼을 보여주는 GET 요청 핸들러
     * "/members/new" URL로 접근하면 createMemberForm.html 뷰를 반환.
     */
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm"; // 회원가입 폼 템플릿 반환
    }

    /**
     * 회원 가입 요청을 처리하는 POST 요청 핸들러
     * "/members/new" URL로 회원 가입 폼에서 입력한 데이터를 받아 처리함.
     */
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member(); // 새로운 회원 객체 생성
        member.setName(form.getName()); // 폼에서 입력한 이름을 회원 객체에 저장

        memberService.join(member); // 회원 가입 서비스 호출

        return "redirect:/"; // 회원가입이 완료되면 홈 화면으로 리다이렉트
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // findMembers()는 member를 다 끄집어 올수있음
        model.addAttribute("members", members);
        // members의 리스트 자체를 medel에다가 담아서 화면에다(view 템플릿에다가) 넘김
        return "members/memberList";
    }

}