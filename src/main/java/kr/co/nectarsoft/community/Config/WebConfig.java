package kr.co.nectarsoft.community.Config;

import kr.co.nectarsoft.community.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description    : 웹 설정
 * packageName    : kr.co.nectarsoft.community.Config
 * fileName       : WebConfig
 * author         : GongSuJeong
 * date           : 2022-06-10
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-10        GongSuJeong       최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/js/**", "/template/**", "/", "index.xml", "/login/**", "/join/**","/error" );
    }
}
