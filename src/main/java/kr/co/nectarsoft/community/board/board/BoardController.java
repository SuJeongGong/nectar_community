package kr.co.nectarsoft.community.board.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    /**
     * methodName : boardList
     * author : Gong SuJeong
     * date : 2022.05.25
     * description : 페이지 연결
     * @param pageName 페이지 연결
     * @return string
     */
    @GetMapping("/{page}.do")
    public String list(@PathVariable("page") String pageName){
        return "board/" + pageName;
    }
}
