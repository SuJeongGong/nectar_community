package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.common.utils.DateUtils;
import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.UserInfoService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : kr.co.nectarsoft.community.user.service.impl
 * fileName       : UserInfoServiceImpl
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User searchUserInfo(String userId) {
        User user = new User();
        user.setId(userId);
        User selectUser = userDAO.selectUserById(user.getId());
        return selectUser;
    }

    @Override
    public void updateUserInfo(User user) {
        userDAO.updateUser(user);
        return;
    }

    @Override
    public void deleteUser(User user) {
        user.setDeleteDate(DateUtils.getNow("dateTime"));
        userDAO.withdrawUser(user);
        return;
    }
}
