package kr.co.nectarsoft.community.user.controller;

import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Map<String,Object>> join(User user){
        Map<String, Object> result = new HashMap<>();
        
        // 비밀번호 확인
        String pwReg = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        if (Pattern.matches(pwReg, user.getPw())) {
            result.put("error", "비밀번호가 조건에 맞지 않습니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        //미인증 유저 테이블에 추가

        
        //이메일 인증 요청 보내기

        //이메일 인증 코드 입력 화면으로 redirect

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
