package kr.co.nectarsoft.community.user.dao;

import kr.co.nectarsoft.community.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * packageName    : kr.co.nectarsoft.community.user.dao
 * fileName       : UserDAO
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    : 유저 관련 DAO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */

@Mapper
public interface UserDAO {
    int selectCountUserById(String id);
    int selectCountUserByEmail(String email);
    int insertUser(User user);
    User selectUserById(User user);
    int updateLoginDate(User user);
    int insertLoginLog(User user);
    int updateUser(User user);
    List<User> selectUsersNotLogined(Map<String, String> map);

    int updateWarnMail(User user);
}
