package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.common.utils.DateUtils;
import kr.co.nectarsoft.community.common.utils.MailUtils;
import kr.co.nectarsoft.community.common.utils.SHA256;
import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * packageName    : kr.co.nectarsoft.community.user.service.impl
 * fileName       : JoinServiceImpl
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */
@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SHA256 sha256;



    @Override
    public boolean checkId(String id) {
        return (userDAO.selectCountUserById(id)<=0) ? true: false;
    }

    @Override
    public boolean checkEmail(String email) {
        return (userDAO.selectCountUserByEmail(email)<=0) ? true: false;
    }

    @Override
    public void addUnAuthUser(User user) {
        user.setLastLoginDate(DateUtils.getNow("dateTime"));
        user.setAuth("USER");
        userDAO.insertUnAuthUser(user);
        return;
    }

    @Override
    public Map<String, Object> sendEmail(String randomNum, User user) throws MessagingException, UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();
        MailUtils sendMail = new MailUtils();
        sendMail.setSubject("[NECTARSOFT] 커뮤니티 이메일 인증메일 입니다."); //메일제목

        sendMail.setText(
                "<h1>메일인증</h1>" +
                        "<br/>"+user.getName()+"님 "+
                        "<br/>[NECTARSOFT] 커뮤니티에 회원가입해주셔서 감사합니다."+
                        "<br/>아래 [인증 코드]를 원래 페이지에 입력해주세요."+
                        "<br/>인증코드[" + randomNum + "]");
        sendMail.setFrom("brightelf9@naver.com");       // 변경해야함
        sendMail.setTo(user.getEmail());
        sendMail.send();
        result.put("result","OK");

        return result;
    }

    @Override
    public Map<String, Object> checkPw(String pw) throws NoSuchAlgorithmException {
        Map<String, Object> result = new HashMap<>();
        
        // 비밀번호 확인
        String pwReg = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        if (!Pattern.matches(pwReg, pw)) {
            result.put("error", "비밀번호가 조건에 맞지 않습니다.");
            result.put("result", "ERROR");
        }else{
            //비밀번호 암호화
            result.put("result", "OK");
            result.put("pw", sha256.encrypt(pw));
        }
        return result;
    }
}
