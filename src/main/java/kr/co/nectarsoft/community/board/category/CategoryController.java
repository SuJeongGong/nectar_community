package kr.co.nectarsoft.community.board.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : kr.co.nectarsoft.community.board.category
 * fileName       : CategoryController
 * author         : GongSuJeong
 * date           : 2022-05-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25        GongSuJeong       최초 생성
 */

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @GetMapping("/{page}.do")
    public String list(@PathVariable String page) {
        return "admin/category/" + page;
    }
}
