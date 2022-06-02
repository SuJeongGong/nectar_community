package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.service.LoginService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

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

    @Autowired
    private LoginService loginService;

    @GetMapping("form.do")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "/user/login";
    }
    @PostMapping("form.do")
    public String login(User user, HttpSession session, Model model){

        try {
            User loginUser = loginService.login(user);
            if (loginUser.getId()==null) {
                model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않거나 탈퇴한 회원 입니다.");
                return"/user/login";
            }
            session.setAttribute("userId", user.getId());
            session.setAttribute("userNickname", user.getNickname());
            session.setMaxInactiveInterval(60*10);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
