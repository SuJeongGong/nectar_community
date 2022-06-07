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
     * description : 유저 추가하기
     * methodName : addUnAuthUser
     * author : Gong SuJeong
     * date : 2022.05.27
     *
     * @param user the user
     */
    public void addUser(User user);

}
