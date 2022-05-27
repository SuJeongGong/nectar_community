package kr.co.nectarsoft.community.user.dao;

import kr.co.nectarsoft.community.user.vo.User;
import org.apache.ibatis.annotations.Mapper;

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
    int insertUnAuthUser(User user);
}
