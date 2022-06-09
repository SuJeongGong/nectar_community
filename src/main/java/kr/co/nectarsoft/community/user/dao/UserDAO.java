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
    /**
     * description : 아이디 개수 체크
     * methodName : selectCountUserById
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param id 개수 체크할 아이디
     * @return int
     */
    int selectCountUserById(String id);

    /**
     * description : 이메일 개수 체크
     * methodName : selectCountUserByEmail
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param email 개수 체크할 이메일
     * @return int
     */
    int selectCountUserByEmail(String email);

    /**
     * description : 유저 추가
     * methodName : insertUser
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user 추가할 유저 정보
     * @return int
     */
    int insertUser(User user);

    /**
     * description : 유저 정보 조회
     * methodName : selectUserById
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param id 조회할 아이디
     * @return user
     */
    User selectUserById(String id);

    /**
     * description : 유저 최근 로그인 날짜, 경고 메일 날짜 update
     * methodName : updateLoginData
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user
     * @return int
     */
    int updateLoginData(User user);

    /**
     * description : 유저 로그인 로그 추가
     * methodName : insertLoginLog
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user 유저 정보
     * @return int
     */
    int insertLoginLog(User user);

    /**
     * description : 유저 정보 수정
     * methodName : updateUser
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user name : 이름, nickname: 닉네임, phone: 전화번호, email:이메일, id: 유저 아이디
     * @return int
     */
    int updateUser(User user);

    /**
     * description : 장기 미로그인 유저 조회 쿼리
     * methodName : selectUsersNotLogined
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param date 지정 날짜(조건검색)
     * @return list
     */
    List<User> selectUsersNotLogined(String date); // 장기 미로그인 유저들

    /**
     * description : 장기 미 로그인 유저에게 메일 보낸 날짜 update
     * methodName : updateWarnMail
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user warnMailDate : 메일전송날짜(UPDATE), id : UPDATE 할 유저
     * @return int
     */
    int updateWarnMailNotLoginUser(User user);

    /**
     * description : 탈퇴 유저 정보 삭제하기
     *          설정 날자보다 이전에 탈퇴한 유저
     * methodName : realDeleteUsers
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param date 설정 날짜
     * @return int
     */
    int deleteUsers(String date); // 탈퇴유저 정보 삭제

    /**
     * description : 장기 미로그인 유저 탈퇴 처리하기
     * methodName : deleteUser
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param map deleteDate : 탈퇴 날짜(UPDATE 할 날짜), date 메일 전송 날짜(비교)
     * @return int
     */
    int withdrawNotLoginUsers(Map<String, String> map);

    /**
     * description : 회원탈퇴
     * methodName : withdrawUser
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param user
     * @return int
     */
    int withdrawUser(User user);
}
