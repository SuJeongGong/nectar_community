package kr.co.nectarsoft.community.user.service;

import kr.co.nectarsoft.community.user.vo.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * description    : 유저 인증 관련 서비스
 * packageName    : kr.co.nectarsoft.community.user.service
 * fileName       : UserAuthService
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
public interface UserAuthService {

    /**
     * description : 랜덤 난수 이메일 보내기
     * methodName : sendEmail
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param user   전송할 대상
     * @throws MessagingException           the messaging exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    String sendEmailRandomNumber(User user) throws MessagingException, UnsupportedEncodingException;
    void sendEmailWarning(User user) throws MessagingException, UnsupportedEncodingException;


}
