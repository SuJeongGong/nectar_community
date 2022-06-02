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

    final String  LOGIN_TERM = String.valueOf(365*2);
    final String  DELETE_TERM = "30";

    @Override
    public void sendMailNotLoginedUser() {
        // 오랫동안 로그인하지 않는 유저 조회하기
        Map<String, String> map = new HashMap<>();
        map.put("today", DateUtils.getNow("today"));
        map.put("term", LOGIN_TERM);
        List<User> users = userDAO.selectUsersNotLogined(map);

        // for문으로 메일 돌리기
        for (User user : users) {
            try {
                userAuthService.sendEmailWarning(user);
                user.setWarnMailDate(DateUtils.getNow("today"));
                userDAO.updateWarnMail(user);
            } catch (Exception e) {
                log.warn("[이메일 전송 실패] userId = {}, userEmail = {}, ", user.getId(), user.getEmail());
            }
        }
        log.warn("로그인 않하는 유저에게 메일 보내기");
    }

    @Override
    public void deleteUserAfterOneMonth() {
        log.warn("탈퇴한 회원 삭제하기");
        //탈퇴한 회원중에 1개월이 된 회원 삭제
        userDAO.realDeleteUsers(DELETE_TERM);
    }

    @Override
    public void deleteUserAfterSendMail() {
        log.warn("메일 보냈는데도 로그인 안한 유저 삭제하기");
        Map<String, String> map = new HashMap<>();
        map.put("today", DateUtils.getNow("today"));
        map.put("term", DELETE_TERM);
        userDAO.deleteUser(map);
    }
}
