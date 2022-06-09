package kr.co.nectarsoft.community.scheduler.service.serviceImpl;

import kr.co.nectarsoft.community.common.utils.DateUtils;
import kr.co.nectarsoft.community.scheduler.service.UserSchedulerService;
import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.UserAuthService;
import kr.co.nectarsoft.community.user.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserAuthService userAuthService;

    //로그인 기간
    final int  LOGIN_TERM_YEAR = 2;
    final int  LOGIN_TERM_MONTH = 0;
    final int  LOGIN_TERM_DAY = 0;

    //메일 보내고 탈퇴처리 기간
    final int  WITHDRAW_TERM_YEAR = 0;
    final int  WITHDRAW_TERM_MONTH = 1;
    final int  WITHDRAW_TERM_DAY = 0;

    //회원 삭제 기간
    final int  DELETE_TERM_YEAR = 0;
    final int  DELETE_TERM_MONTH = 1;
    final int  DELETE_TERM_DAY = 0;

    @Override
    public void sendMailNotLoginedUser() {
        // 오랫동안 로그인하지 않는 유저 조회하기
        List<User> users = userDAO.selectUsersNotLogined(DateUtils.calculateDate(-LOGIN_TERM_YEAR,-LOGIN_TERM_MONTH,-LOGIN_TERM_DAY) + "000000");

        // for문으로 메일 돌리기
        for (User user : users) {
            try {
                userAuthService.sendEmailWarning(user);
                user.setWarnMailDate(DateUtils.getNow("dateTime"));
                userDAO.updateWarnMail(user);
                log.warn("[이메일 전송 성공] userId = {}, userEmail = {}, ", user.getId(), user.getEmail());
            } catch (Exception e) {
                log.warn("[이메일 전송 실패] userId = {}, userEmail = {}, ", user.getId(), user.getEmail());
            }
        }
        log.warn("로그인 안하는 유저에게 메일 보내기");
    }

    @Override
    public void deleteUserAfterOneMonth() {
        log.warn("탈퇴한 회원 삭제하기");
        userDAO.deleteUsers(DateUtils.calculateDate(-DELETE_TERM_YEAR,-DELETE_TERM_MONTH,-DELETE_TERM_DAY) + "000000");
    }

    @Override
    public void withdrawUserAfterSendMail() {
        log.warn("메일 보냈는데도 로그인 안한 유저 탈퇴 처리하기");
        Map<String, String> map = new HashMap<>();
        map.put("deleteDate", DateUtils.getNow("dateTime"));
        map.put("date", DateUtils.calculateDate(-WITHDRAW_TERM_YEAR,-WITHDRAW_TERM_MONTH,-WITHDRAW_TERM_DAY) + "000000");
        userDAO.withdrawUser(map);
    }
}
