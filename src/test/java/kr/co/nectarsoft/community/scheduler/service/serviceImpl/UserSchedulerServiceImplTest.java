package kr.co.nectarsoft.community.scheduler.service.serviceImpl;

import kr.co.nectarsoft.community.scheduler.service.UserSchedulerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description    : 스케쥴러 기능 테스트
 * packageName    : kr.co.nectarsoft.community.scheduler.service.serviceImpl
 * fileName       : UserSchedulerServiceImplTest
 * author         : GongSuJeong
 * date           : 2022-06-08
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-08        GongSuJeong       최초 생성
 */
@SpringBootTest
class UserSchedulerServiceImplTest {
    @Autowired
    UserSchedulerService userSchedulerService;
    /**
     * 장기 미 로그인 유저에게 메일 보내는 테스트
     */
    @Test
    void sendMailNotLoginedUser_메일_보내는지_확인() {
        userSchedulerService.sendMailNotLoginedUser();

        //TODO 검증하는 코드 넣기
    }

    @Test
    void deleteUserAfterOneMonth_탈퇴한회원_삭제_처리하는지_확인() {
        userSchedulerService.deleteUserAfterOneMonth();

        //TODO 검증하는 코드 넣기
    }

    @Test
    void deleteUserAfterSendMail_메일_보낸유저_탈퇴처리() {
        userSchedulerService.withdrawUserAfterSendMail();

        //TODO 검증하는 코드 넣기
    }
}