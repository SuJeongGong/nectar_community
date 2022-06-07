package kr.co.nectarsoft.community.user.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * description    :
 * packageName    : kr.co.nectarsoft.community.user.form
 * fileName       : UserInfoForm
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
public class UserInfoForm {
    @NotBlank
    private String id;

//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    private String pw;


    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone;

    private String message;
}
