package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.form.LoginForm;
import kr.co.nectarsoft.community.user.service.LoginService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * description : 로그인 화면 연결
     * methodName : loginForm
     * author : Gong SuJeong
     * date : 2022.06.03
     *
     * @return string
     */
    @GetMapping("form.do")
    public String loginForm(@ModelAttribute("user") LoginForm form){
        return "/user/login";
    }

    /**
     * description : 로그인 처리
     * methodName : login
     * author : Gong SuJeong
     * date : 2022.06.07
     *
     * @param form
     * @param session
     * @param bindingResult
     * @return string
     */
    @PostMapping("form.do")
    public String login(@Validated @ModelAttribute("user") LoginForm form, BindingResult bindingResult
            , HttpSession session, HttpServletRequest request, @RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL){
        //TODO redirectURL 변수명 const로 관리하기, redirectURL 값이 제대로 안들어옴 확인하기
        if (bindingResult.hasErrors()) {
            form.setMessage("오류가 발생했습니다.");
            return "/user/login";
        }

        User user = new User();
        user.setId(form.getId());
        user.setPw(form.getPw());
        user.setIp(request.getRemoteAddr());
        try {
            User loginUser = loginService.login(user);
            if (loginUser.getId()==null) {//service에서 체크 후 안맞으면 new User()리턴이라
                form.setMessage("아이디 또는 비밀번호가 일치하지 않거나 탈퇴한 회원 입니다.");
                return"/user/login";
            }
            session.setAttribute("userId", user.getId());
            session.setAttribute("userNickname", user.getNickname());
            session.setMaxInactiveInterval(60*10);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "redirect:" + redirectURL;
    }

    /**
     * description : 로그아웃 처리
     * methodName : logout
     * author : Gong SuJeong
     * date : 2022..
     *
     * @return string
     */
    @GetMapping("logout.do")
    public String logout(HttpSession session){
        session.invalidate(); // 세션 초기화
        return "redirect:/";
    }
}
