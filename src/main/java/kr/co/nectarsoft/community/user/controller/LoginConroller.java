package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description    : 로그인 관련 컨트롤러
 * packageName    : kr.co.nectarsoft.community.user
 * fileName       : LoginConroller
 * author         : GongSuJeong
 * date           : 2022-05-27
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */

@RequestMapping("/login/*")
@Controller
public class LoginConroller {

    @GetMapping("form.do")
    public String joinForm(Model model){
        model.addAttribute("user", new User());
        return "/user/login";
    }
}
