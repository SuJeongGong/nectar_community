package kr.co.nectarsoft.community.user.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

/**
 * description    : 로그인 화면 폼
 * packageName    : kr.co.nectarsoft.community.user.form
 * fileName       : LoginForm
 * author         : GongSuJeong
 * date           : 2022-06-07
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07        GongSuJeong       최초 생성
 */
@Component
@Getter
@Setter
public class LoginForm {
    @NotBlank
    private String id;
    @NotBlank
    private String pw;

    private String message;
}
