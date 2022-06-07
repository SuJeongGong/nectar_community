/**
 * description    : 자주 사용하는 정규식
 * fileName       : checkReg
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */
//이메일 체크
const checkEmailReg = /.+@[a-zA-Z]+(\.[a-zA-Z]+){1,2}/g;

//비밀번호 체크
const checkPwReg = /(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}/g;