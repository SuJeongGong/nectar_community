package kr.co.nectarsoft.community.user.service;

import kr.co.nectarsoft.community.user.vo.User;

/**
 * description    : 마이페이지 관련 서비스
 * packageName    : kr.co.nectarsoft.community.user.service
 * fileName       : UserInfoService
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
public interface UserInfoService {
    /**
     * description : 유저 정보 찾기
     * methodName : searchUserInfo
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param userId
     * @return user
     */
    public User searchUserInfo(String userId);

    /**
     * description : 유저 정보 변경
     * methodName : updateUserInfo
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param user
     */
    public void updateUserInfo(User user);

    /**
     * description : 유저 회원 탈퇴
     * methodName : deleteUser
     * author : Gong SuJeong
     * date : 2022.06.02
     *
     * @param user
     */
    public void deleteUser(User user);
}
