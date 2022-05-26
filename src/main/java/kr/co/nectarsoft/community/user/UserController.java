package kr.co.nectarsoft.community.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : kr.co.nectarsoft.community.user
 * fileName       : UserController
 * author         : GongSuJeong
 * date           : 2022-05-26
 * description    : 유저 관련 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-26        GongSuJeong       최초 생성
 */

@RequestMapping("/user")
@Controller
public class UserController {
    @GetMapping("login.do")
    public String loginForm(){
        return "thymeleaf/user/login";
    }
    @GetMapping("join.do")
    public String joinForm(){
        return "thymeleaf/user/join";
    }
}
