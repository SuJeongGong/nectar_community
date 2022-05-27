package kr.co.nectarsoft.community.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : kr.co.nectarsoft.community.user
 * fileName       : LoginConroller
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    : 로그인 관련 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */

@RequestMapping("/user/*")
@Controller
public class LoginConroller {



    @GetMapping("login.do")
    public String joinForm(){
        return "/user/login";
    }
}
