package kr.co.nectarsoft.community.user.service.impl;

import kr.co.nectarsoft.community.user.dao.UserDAO;
import kr.co.nectarsoft.community.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return (userDAO.countUserById(id)<=0) ? true: false;
    }

    @Override
    public boolean checkEmail(String email) {
        return (userDAO.countUserByEmail(email)<=0) ? true: false;
    }
}
