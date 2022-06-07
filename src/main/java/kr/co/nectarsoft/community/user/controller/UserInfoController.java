package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.form.UserInfoForm;
import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.service.UserInfoService;
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
import java.util.regex.Pattern;

/**
 * description    : 마이페이지 관련 컨트롤러
 * packageName    : kr.co.nectarsoft.community.user.controller
 * fileName       : UserInfoController
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
@RequestMapping("/user/*")
@Controller
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    private UserAuthService userAuthService;


    /**
     * description : 회원 정보 수정 페이지 이동
     * methodName : userInfo
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param session
     * @param model
     * @return string
     */
    @GetMapping("mypage.do")
    public String userInfo(HttpSession session, Model model){
        User user = userInfoService.searchUserInfo(String.valueOf(session.getAttribute("userId")));
        model.addAttribute("user", user);
        return "/user/userInfo";
    }


    /**
     * description : 회원정보 수정( 이메일 변경이면 이메일 인증 후에 변경 )
     * methodName : userInfoUpdate
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param form  입력데이터
     * @param bindingResult 검증결과
     * @param model
     * @param session
     * @return string
     */
    @PostMapping("mypage.do")
    public String userInfoUpdate(@Validated @ModelAttribute("user") UserInfoForm form, BindingResult bindingResult, Model model, HttpSession session){

        if (bindingResult.hasErrors()) {
            form.setMessage("누락된 값이 있습니다.");
            return "/user/userInfo";
        }

        //세션에 있는 아이디(=로그인정보)와 변경하려는 아이디 값이 다르면!
        if(!String.valueOf(session.getAttribute("userId")).equals(form.getId())){
            form.setMessage("로그인 정보와 수정 회원 정보가 다릅니다.");
            return "/user/userInfo";
        }

        if (form.getPw() != null && !"".equals(form.getPw())) {
            if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", form.getPw())){
                form.setMessage("비밀번호가 조건에 맞지 않습니다.");
                return "/user/userInfo";
            }
        }



        User user = new User();
        user.setId(String.valueOf(session.getAttribute("userId")));
        user.setNickname(form.getNickname());
        user.setPhone(form.getPhone());
        user.setName(form.getName());
        try {
            user.setPw(userAuthService.pwEncryption(form.getPw()));
        } catch (NoSuchAlgorithmException e) {
            form.setMessage("오류가 발생했습니다.");
            return "/user/userInfo";
        }

        //이메일이 변경 되었다면
        User originUser = userInfoService.searchUserInfo(String.valueOf(session.getAttribute("userId")));
        if(!originUser.getEmail().equals(form.getEmail())){
            //이메일 인증 요청 보내기
            try {
                user.setEmail(form.getEmail());
                String randomNum = userAuthService.sendEmailRandomNumber(user);
                //세션에 인증번호 넣기
                session.setAttribute("emailKey", randomNum);
                session.setAttribute("user",user); // 변경해야함 , 비밀번호 암호화 필요
                return "redirect:/user/authentication.do";
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            userInfoService.updateUserInfo(user);
        }
        return "redirect:/user/mypage.do";
    }
    /**
     * description : 인증번호 입력하는 페이지 연결
     * methodName : authentication
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @return string
     */
    @GetMapping("authentication.do")
    public String authentication(Model model){
        model.addAttribute("link", "/user/authentication.do");
        model.addAttribute("resendLink", "/user/resend/email");
        return "/user/authentication";
    }

    /**
     * description : 이메일 인증시 회원 정보 수정
     * methodName : authenticationCheck
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param session
     * @param userKey 이메일 인증 키
     * @return string
     */
    @PostMapping("authentication.do")
    public String authenticationCheck(HttpSession session, @RequestParam("emailKey") String userKey){
        String emailKey = String.valueOf(session.getAttribute("emailKey"));
        if (emailKey.equals(userKey)) {
            User user = (User) session.getAttribute("user");
            userInfoService.updateUserInfo(user);
            session.invalidate();
        }

        return "redirect:/user/mypage.do";
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

    /**
     * description : 회원탈퇴 처리
     * methodName : deleteUser
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param user
     * @return string
     */
    @PostMapping("/deleteUser.do")
    public String deleteUser(User user){
        userInfoService.deleteUser(user);
        return "redirect:/";
    }
}
