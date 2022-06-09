package kr.co.nectarsoft.community.scheduler.service;

/**
 * description    : 유저 관련 스케쥴
 * packageName    : kr.co.nectarsoft.community.scheduler
 * fileName       : UserSchedulerService
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
public interface UserSchedulerService {
    /**
     * description : 장기 미로그인 회원 메일 전송
     * methodName : sendMailNotLoginedUser
     * author : Gong SuJeong
     * date : 2022.06.09
     */
    public void sendMailNotLoginedUser();

    /**
     * description : 탈퇴 회원 삭제 처리
     * methodName : deleteUserAfterOneMonth
     * author : Gong SuJeong
     * date : 2022.06.09
     */
    public void deleteUserAfterOneMonth();

    /**
     * description : 회원 탈퇴
     * methodName : userWithdrawAfterSendMail
     * author : Gong SuJeong
     * date : 2022.06.09
     */
    public void withdrawUserAfterSendMail();
}
