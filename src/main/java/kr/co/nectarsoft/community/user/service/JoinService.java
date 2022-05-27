package kr.co.nectarsoft.community.user.service;

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
     * methodName : checkId
     * author : Gong SuJeong
     * date : 2022.05.27
     * description : 아이디 존재하는지 확인하기
     *
     * @return boolean
     */
    public boolean checkId(String id);

    /**
     * methodName : checkEmail
     * author : Gong SuJeong
     * date : 2022.05.27
     * description : 이메일 존재하는지 확인하기
     *
     * @return boolean
     */
    public boolean checkEmail(String email);
}
