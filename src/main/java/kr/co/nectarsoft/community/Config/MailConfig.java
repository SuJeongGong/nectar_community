package kr.co.nectarsoft.community.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * packageName    : kr.co.nectarsoft.community.Config
 * fileName       : MailConfig
 * author         : GongSuJeong
 * date           : 2022-05-30
 * description    : 메일 관련 빈 등록하는 설정 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-30        GongSuJeong       최초 생성
 */
@Configuration
public class MailConfig {
    @Value("${spring.mail.host}") private String host;
    @Value("${spring.mail.username}") private String id;
    @Value("${spring.mail.password}") private String pw;
    @Value("${spring.mail.port}") private int port;

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(id);
        mailSender.setPassword(pw);
        mailSender.setPort(port);
        return mailSender;
    }
}
