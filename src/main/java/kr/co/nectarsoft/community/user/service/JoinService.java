package kr.co.nectarsoft.community.user.service;

import kr.co.nectarsoft.community.user.vo.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * packageName    : kr.co.nectarsoft.community.user.service
 * fileName       : JoinService
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    : 회원가입 관련 서비스 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */
public interface JoinService {

    /**
     * description : 아이디 존재하는지 확인하기
     * methodName : checkId
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param id the id
     * @return boolean boolean
     */
    public boolean checkId(String id);

    /**
     * description : 이메일 존재하는지 확인하기
     * methodName : checkEmail
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param email the email
     * @return boolean boolean
     */
    public boolean checkEmail(String email);

    /**
     * description : 미인증 유저 추가하기
     * methodName : addUnAuthUser
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param user the user
     */
    public void addUnAuthUser(User user);


    /**
     * description : 이메일 보내기
     * methodName : sendEmail
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param randomNum 인증 번호
     * @param user   전송할 대상
     * @throws MessagingException           the messaging exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public void sendEmail(String randomNum, User user) throws MessagingException, UnsupportedEncodingException;

    /**
     * description : 비밀번호 확인 후 암호화
     * methodName : checkPw
     * author : Gong SuJeong
     * date : 2022.05.29
     *
     * @param pw 비밀번호
     * @return map 비밀번호 확인 결과 및 암호화 비밀번호
     */
    public Map<String, Object> checkPw(String pw) throws NoSuchAlgorithmException;
}
