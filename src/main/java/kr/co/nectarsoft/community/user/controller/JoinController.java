package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * description    : 회원가입 컨트롤러
 * packageName    : kr.co.nectarsoft.community.user
 * fileName       : JoinController
 * author         : GongSuJeong
 * date           : 2022-05-26
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-26        GongSuJeong       최초 생성
 */
@Controller
@RequestMapping("/join/*")
public class JoinController {

    @Autowired
    private JoinService joinService;
    @Autowired
    private UserAuthService userAuthService;

    /**
     * description : 페이지 연결
     * methodName : join
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @return string
     */
    @GetMapping("form.do")
    public String joinForm(Model model){
        model.addAttribute("user", new User());
        return "/user/join";
    }

    /**
     * description : 회원가입 처리를 위한 이메일 인증 발송
     * methodName : join
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @return string
     */
    @PostMapping("form.do")
    public String join(User user, HttpSession session, Model model){
        Map<String, Object> result = new HashMap<>();

        // 비밀번호 확인
        try {
            Map<String, Object> resultPw = joinService.checkPw(user.getPw());
            if ("ERROR".equals(resultPw.get("result"))) {
                model.addAttribute("user", user);
                model.addAttribute("message", "비밀번호가 조건에 맞지 않습니다.");
                return "/user/join";
            }
            user.setPw(String.valueOf(resultPw.get("pw")));
        } catch (NoSuchAlgorithmException e) {
            model.addAttribute("user", user);
            model.addAttribute("message", "오류가 발생했습니다.");
            return "/user/join";
        }
        
        //이메일 인증 요청 보내기

        try {
            String randomNum = userAuthService.sendEmail(user);
            //세션에 인증번호 넣기
            session.setAttribute("emailKey", randomNum);
            session.setAttribute("user",user); // 변경해야함 , 비밀번호 암호화 필요
            return "redirect:/join/authentication.do";
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "오류가 발생했습니다.");
        return "/user/join";
    }

    /**
     * description : 중복체크
     * methodName : checkId
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param type id/ email
     * @param value 체크할 id나 email값
     * @return Boolean 결과 값 가능하면 true / 불가능 false / null -id, email 외에
     */
    @GetMapping("check/{type}/{value}")
    public ResponseEntity<Boolean> check(@PathVariable String type, @PathVariable String value){
        if("id".equals(type)){
            return new ResponseEntity<>(joinService.checkId(value), HttpStatus.OK);
        }else if("email".equals(type)){
            return new ResponseEntity<>(joinService.checkEmail(value), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * description : 인증번호 입력하는 페이지 연결
     * methodName : authentication
     * author : Gong SuJeong
     * date : 2022.05.31
     *
     * @return string
     */
    @GetMapping("/authentication.do")
    public String authentication(Model model){
        model.addAttribute("link", "/join/authentication.do");
        return "/user/authentication";
    }

    @PostMapping("/authentication.do")
    public String authenticationCheck(HttpSession session, @RequestParam("emailKey") String userKey){
        String emailKey = String.valueOf(session.getAttribute("emailKey"));
        if (userKey.equals(emailKey)) {
            User user = (User) session.getAttribute("user");
            joinService.addUser(user);
            session.invalidate();
        }

        return "redirect:/login/form.do";
    }



}
