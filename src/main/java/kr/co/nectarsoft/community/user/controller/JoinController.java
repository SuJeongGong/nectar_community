package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
@RequestMapping("/join/*")
@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    /**
     * 페이지 연결
     *
     * @return
     */
    @GetMapping("form.do")
    public String joinForm(){
        return "/user/join";
    }


    /**
     * methodName : join
     * author : Gong SuJeong
     * date : 2022.05.27
     * description : 실제 회원가입 처리
     *
     * @return string
     */
    @PostMapping("form.do")
    public String join(){
        return "";
    }

    /**
     * methodName : checkId
     * author : Gong SuJeong
     * date : 2022.05.27
     * description : id 중복체크
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
