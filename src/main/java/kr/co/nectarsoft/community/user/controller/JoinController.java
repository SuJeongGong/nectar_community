package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * packageName    : kr.co.nectarsoft.community.user
 * fileName       : JoinController
 * author         : GongSuJeong
 * date           : 2022-05-26
 * description    : 회원가입 컨트롤러
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
    public String join(User user, HttpSession session, Model model, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();

        // 비밀번호 확인
        try {
            Map<String, Object> resultPw = joinService.checkPw(user.getPw());
            if ("ERROR".equals(resultPw.get("result"))) {
                return sendAlertPage("비밀번호가 조건에 맞지 않습니다.",model, request);
            }
        } catch (NoSuchAlgorithmException e) {
            return sendAlertPage("오류가 발생했습니다.",model, request);
        }
        
        //이메일 인증 요청 보내기
        String randomNum = newRandomNumber();
        Map<String, Object> emailRes;
        try {
            joinService.sendEmail(randomNum, user);
            //세션에 인증번호 넣기
            session.setAttribute("emailKey", randomNum);
            session.setAttribute("emailKey", randomNum); // 변경해야함 , 비밀번호 암호화 필요
            return "/user/authentication";
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sendAlertPage("오류가 발생했습니다.",model, request);
    }

    //랜덤 인증번호 생성
    private String newRandomNumber() {
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            randomNum += String.valueOf((Math.random() * 10000) % 10);
        }
        return randomNum;
    }

    //alert page로 이동하는 메서드 만들기
    private String sendAlertPage( String message,Model model, HttpServletRequest request) {
        model.addAttribute("message", message);
        model.addAttribute("redirectURL", request.getRequestURI());
        return "/common/alertPage";
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
    
}
