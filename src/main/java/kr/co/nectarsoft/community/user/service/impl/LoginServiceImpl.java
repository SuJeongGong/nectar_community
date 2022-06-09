package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.common.utils.DateUtils;
import kr.co.nectarsoft.community.common.utils.SHA256;
import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.LoginService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * description    : 로그인 서비스 구현체
 * packageName    : kr.co.nectarsoft.community.user.service.impl
 * fileName       : LoginServiceImpl
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SHA256 sha256;

    @Override
    public User login(User user) throws NoSuchAlgorithmException {
        User selectUser = userDAO.selectUserById(user.getId());
        if ("T".equals(user.getDeleteAt())
                || !sha256.encrypt(user.getPw()).equals(selectUser.getPw()) ) {
            return new User();
        }

        selectUser.setLastLoginDate(DateUtils.getNow("dateTime"));
        selectUser.setIp(user.getIp());
        userDAO.updateLoginData(selectUser);
        userDAO.insertLoginLog(selectUser);

        return selectUser;
    }
}
