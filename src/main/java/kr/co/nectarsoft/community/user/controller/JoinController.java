package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.form.JoinForm;
import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        model.addAttribute("user", new JoinForm());
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
    public String join(@Validated @ModelAttribute("user") JoinForm form, BindingResult bindingResult,HttpSession session){
        //검증
        if(bindingResult.hasErrors()){
            return "/user/join";
        }
        
        //user로 변환
        User user = new User();
        user.setId(form.getId());
        user.setEmail(form.getEmail());
        user.setName(form.getName());
        user.setNickname(form.getNickname());
        user.setAuth("USER");
        user.setPhone(form.getPhone());
        try {
            user.setPw(userAuthService.pwEncryption(form.getPw()));
        } catch (NoSuchAlgorithmException e) {
            form.setMessage("오류가 발생했습니다.");
            return "/user/join";
        }

        //이메일 인증 요청 보내기

        try {
            String randomNum = userAuthService.sendEmailRandomNumber(user);
            
            //인증번호 저장
            session.setAttribute("emailKey", randomNum);
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(60*10);
            return "redirect:/join/authentication.do";
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        form.setMessage("오류가 발생했습니다.");
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
    @GetMapping("authentication.do")
    public String authentication(Model model){
        model.addAttribute("link", "/join/authentication.do");
        model.addAttribute("resendLink", "'/join/resend/email'");
        return "/user/authentication";
    }

    /**
     * description : 인증번호 인증 처리
     * methodName : authenticationCheck
     * author : Gong SuJeong
     * date : 2022.06.07
     *
     * @param session
     * @param userKey
     * @return string
     */
    @PostMapping("authentication.do")
    public String authenticationCheck(HttpSession session, @RequestParam("emailKey") String userKey){
        String emailKey = String.valueOf(session.getAttribute("emailKey"));
        if (userKey.equals(emailKey)) {
            User user = (User) session.getAttribute("user");
            joinService.addUser(user);
            session.invalidate();
        }

        return "redirect:/login/form.do";
    }

    /**
     * description : 이메일 재전송
     * methodName : sendEmail
     * author : Gong SuJeong
     * date : 2022.06.07
     *
     * @param session
     * @return response entity
     */
    @ResponseBody
    @PostMapping("resend/email")
    public ResponseEntity<Boolean> sendEmail(HttpSession session){
        User user = (User)session.getAttribute("user");
        try {
            String randomNum = userAuthService.sendEmailRandomNumber(user);

            //인증번호 저장
            session.setAttribute("emailKey", randomNum);
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(60*10); // 시간 재설정
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
