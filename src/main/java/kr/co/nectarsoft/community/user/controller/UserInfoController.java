package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.service.UserInfoService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

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
     * description : 회원정보 수정( 이메일 있으면 이메일 인증 후에 변경 )
     * methodName : userInfoUpdate
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param user 회원정보
     * @param model
     * @param session
     * @return string
     */
    @PostMapping("mypage.do")
    public String userInfoUpdate(User user, Model model, HttpSession session){
        User originUser = userInfoService.searchUserInfo(user.getId());

        //이메일이 변경 되었다면
        if(!originUser.getEmail().equals(user.getEmail())){
            //이메일 인증 요청 보내기
            try {
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
        }

        try {
            userInfoService.updateUserInfo(user);
            User newUser = userInfoService.searchUserInfo(user.getId());
            model.addAttribute("user", newUser);
        }
        catch(Exception e){
            model.addAttribute("user", user);
        }
        return "/user/userInfo";
    }
    /**
     * description : 인증번호 입력하는 페이지 연결
     * methodName : authentication
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @return string
     */
    @GetMapping("/authentication.do")
    public String authentication(Model model){
        model.addAttribute("link", "/user/authentication.do");
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
    @PostMapping("/authentication.do")
    public String authenticationCheck(HttpSession session, @RequestParam("emailKey") String userKey){
        String emailKey = String.valueOf(session.getAttribute("emailKey"));
        if (userKey.equals(emailKey)) {
            User user = (User) session.getAttribute("user");
            userInfoService.updateUserInfo(user);
            session.invalidate();
        }

        return "redirect:/user/mypage.do";
    }

    @PostMapping("/deleteUser.do")
    public String deleteUser(User user){
        userInfoService.deleteUser(user);
        return "redirect:/";
    }
}
