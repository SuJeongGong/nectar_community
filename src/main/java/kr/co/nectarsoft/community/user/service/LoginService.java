package kr.co.nectarsoft.community.user.service;

import kr.co.nectarsoft.community.user.vo.User;

import java.security.NoSuchAlgorithmException;

/**
 * description    : 로그인 서비스
 * packageName    : kr.co.nectarsoft.community.user.service
 * fileName       : LoginService
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
public interface LoginService {
    public User login(User user) throws NoSuchAlgorithmException;
}
