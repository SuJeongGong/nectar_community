package kr.co.nectarsoft.community.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * description    : 로그인 체크하는 인터셉터
 * packageName    : kr.co.nectarsoft.community.interceptor
 * fileName       : LoginCheckInterceptor
 * author         : GongSuJeong
 * date           : 2022-06-10
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-10        GongSuJeong       최초 생성
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 실행 = {}", requestURI);

        HttpSession session = request.getSession(false);
        
        
        // TODO 세션이름을 String ->  세션이름 interface 만들어서 상수로 저장한 세션이름으로 변경하기
        // 로그인 체크
        if (session == null || session.getAttribute("userId") == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login/form.do?redirectURL="+requestURI);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            log.error("에러 발생 ",ex);
        }
    }
}
