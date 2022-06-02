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
    public void sendMailNotLoginedUser();
    public void deleteUserAfterOneMonth();
    public void deleteUserAfterSendMail();
}
