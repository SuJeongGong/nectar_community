package kr.co.nectarsoft.community.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
    public int countUserById(String id);
    public int countUserByEmail(String email);
}
