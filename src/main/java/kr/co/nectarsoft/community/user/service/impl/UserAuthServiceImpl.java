package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.common.utils.MailUtils;
import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * description    : 유저 인증 관련 서비스
 * packageName    : kr.co.nectarsoft.community.user.service.impl
 * fileName       : UserAuthServiceImpl
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private MailUtils sendMail;
    @Value("${send.mail.address}") String sendMailAddress;
    /**
     * description : 랜덤 번호 생성
     * methodName : newRandomNumber
     * author : Gong SuJeong
     * date :  2022.06.02
     */
    private String newRandomNumber() {
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            randomNum += String.valueOf((int)Math.floor(Math.random() * 10));
        }
        return randomNum;
    }

    @Override
    public String sendEmailRandomNumber(User user) throws MessagingException, UnsupportedEncodingException {
        String randomNum = newRandomNumber();

        sendMail.setSubject("[NECTARSOFT] 커뮤니티 이메일 인증메일 입니다.\n"); //메일제목
        sendMail.setText(
                "[회원가입 메일인증]" +
                        "\n"+user.getName()+"님  [NECTARSOFT] 커뮤니티에 회원가입해주셔서 감사합니다."+
                        "\n아래 [인증 코드]를 원래 페이지에 입력해주세요."+
                        "\n인증코드 : " + randomNum +
                        "\n만약 본인이 회원가입한게 아니라면 무시하셔도 됩니다.");
        sendMail.setFrom("0_sujeong@naver.com");       // 변경할 수 없으려나?!
        sendMail.setTo(user.getEmail());
        sendMail.send();

        return randomNum;
    }

    @Override
    public void sendEmailWarning(User user) throws MessagingException, UnsupportedEncodingException {

        sendMail.setSubject("[NECTARSOFT] 커뮤니티 장기 미로그인 유저 탈퇴 경고 메일 입니다..\n"); //메일제목
        sendMail.setText(
                "[회원가입 메일인증]" +
                        "\n아래 [인증 코드]를 원래 페이지에 입력해주세요."+
                        "\n"+user.getName()+"님  안녕하세요. [NECTARSOFT] 커뮤니티 관리자 입니다." +
                        "\n[NECTARSOFT] 커뮤니티에 장기 미로그인 사용자로 한달 이내에 재 로그인을 하지 않으시면 회원 탈퇴가 진행됩니다.");
        sendMail.setFrom("0_sujeong@naver.com");       // 변경할 수 없으려나?!
        sendMail.setTo(user.getEmail());
        sendMail.send();

        return;
    }

}
