package kr.co.nectarsoft.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description    : 메인페이지와 웰컴 페이지 관리하는 컨트롤러
 * packageName    : kr.co.nectarsoft.community
 * fileName       : homeController
 * author         : GongSuJeong
 * date           : 2022-06-10
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-10        GongSuJeong       최초 생성
 */

@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "/home/main";
    }
}
