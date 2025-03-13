package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 HTML 파일을 이용한 기본적인 응답 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 뷰에서 사용할 데이터 추가
        return "hello";
    }

    // URL 파라미터를 받아서 뷰 템플릿에 데이터를 전달하는 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // 모델에 name 값 추가
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 뷰를 거치지 않고 HTTP 응답 본문(body)에 직접 데이터를 넣어 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // "hello [입력한 이름]" 형태의 문자열 응답
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체를 JSON 형식으로 변환하여 HTTP 응답 본문에 넣어 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;  // name 속성 (JSON 필드)

        public String getName() { // Getter
            return name;
        }

        public void setName(String name) {  // Setter
            this.name = name;
        }
    }
}