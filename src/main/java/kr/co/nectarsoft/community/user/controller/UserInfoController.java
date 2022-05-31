package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * description    : 마이페이지 관련 컨트롤러
 * packageName    : kr.co.nectarsoft.community.user.controller
 * fileName       : UserInfo
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
@RequestMapping("/user/*")
@Controller
public class UserInfo {
    @Autowired


    @GetMapping("mypage.do")
    public String userInfo(HttpSession session, Model model){

        User user = new User();
        user.setId(String.valueOf(session.getAttribute("userId")));
        userDAO.selectUser(user);

        model.addAttribute("user", );
        return "/user/userInfo";
    }
}
