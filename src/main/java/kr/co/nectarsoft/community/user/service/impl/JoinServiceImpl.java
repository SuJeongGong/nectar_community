package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.common.utils.DateUtils;
import kr.co.nectarsoft.community.common.utils.MailUtils;
import kr.co.nectarsoft.community.common.utils.SHA256;
import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.JoinService;
import kr.co.nectarsoft.community.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * packageName    : kr.co.nectarsoft.community.user.service.impl
 * fileName       : JoinServiceImpl
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */
@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    private UserDAO userDAO;



    @Override
    public boolean checkId(String id) {
        return (userDAO.selectCountUserById(id)<=0) ? true: false;
    }

    @Override
    public boolean checkEmail(String email) {
        return (userDAO.selectCountUserByEmail(email)<=0) ? true: false;
    }

    @Override
    public void addUser(User user) {
        user.setLastLoginDate(DateUtils.getNow("dateTime"));
        user.setAuth("USER");
        userDAO.insertUser(user);
        return;
    }

}
