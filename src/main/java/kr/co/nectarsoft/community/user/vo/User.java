package kr.co.nectarsoft.community.user.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : kr.co.nectarsoft.community.user.vo
 * fileName       : User
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    : 유저 관련 정보
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */

@Getter @Setter
public class User {
    private String id;
    private String pw;
    private String email;
    private String name;
    private String nickname;
    private String auth;
    private String lastLoginDate;
    private String phone;
    private String deleteAt;
    private String deleteDate;
    private String ip;
}
