package kr.co.nectarsoft.community.scheduler.service.serviceImpl;

import kr.co.nectarsoft.community.scheduler.service.UserSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : kr.co.nectarsoft.community.scheduler.service.serviceImpl
 * fileName       : UserSchedulerServiceImpl
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
@Slf4j
@Service
public class UserSchedulerServiceImpl implements UserSchedulerService {
    @Override
    public void sendMailNotLoginedUser() {
        log.warn("로그인 않하는 유저에게 메일 보내기");
    }

    @Override
    public void deleteUserAfterOneMonth() {
        log.warn("탈퇴한 회원 삭제하기");
    }

    @Override
    public void deleteUserAfterSendMail() {
        log.warn("메일 보냈는데도 로그인 안한 유저 삭제하기");
    }
}
