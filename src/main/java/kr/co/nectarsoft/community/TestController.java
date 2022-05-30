package kr.co.nectarsoft.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : kr.co.nectarsoft.community
 * fileName       : TestController
 * author         : GongSuJeong
 * date           : 2022-05-26
 * description    : 과제 테스트용 컨트롤러 삭제해도 무방
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-26        GongSuJeong       최초 생성
 */
@Controller
public class TestController {

    @GetMapping("/test.do")
    public String test() {
        return "test";
    }
}
