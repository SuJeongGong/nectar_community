package kr.co.nectarsoft.community.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * packageName      : kr.co.nectarsoft.community.common
 * date             : 2022-05-27
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      : 메일 관련 클래스
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-05-27       SuJeong Gong        최초작성
 */
public class MailUtils {
    @Value("${spring.mail}")
    private JavaMailSender mailSender;//메일 sender
    private SimpleMailMessage message = new SimpleMailMessage();//메일 내용


    /**
     * description : 메일 제목 설정
     * methodName : setSubject
     * author : Gong SuJeong
     * date :  2022.05.29
     *
     * @param subject 메일 제목
     * @throws MessagingException the messaging exception
     */
    public void setSubject(String subject) throws MessagingException {
        this.message.setSubject(subject);
    }

    /**
     * description : 메일 내용 설정
     * methodName : setText
     * author : Gong SuJeong
     * date :  2022.05.29
     *
     * @param htmlContent 메일 내용
     * @throws MessagingException the messaging exception
     */
    public void setText(String htmlContent) throws MessagingException {
        this.message.setText(htmlContent);
    }

    /**
     * description : 보내는 사람 메일 설정
     * methodName : setFrom
     * author : Gong SuJeong
     * date :  2022.05.29
     *
     * @param email 보내는 사람 메일
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException           the messaging exception
     */
    public void setFrom(String email) throws UnsupportedEncodingException, MessagingException {
        this.message.setFrom(email);
    }

    /**
     * description : 받는 사람 메일 설정
     * methodName : setTo
     * author : Gong SuJeong
     * date :  2022.05.29
     *
     * @param email 받는 사람 메일
     * @throws MessagingException the messaging exception
     */
    public void setTo(String email) throws MessagingException {
        this.message.setTo(email);
    }

    /**
     * description : 메일 보내기
     * methodName : send
     * author : Gong SuJeong
     * date :  2022.05.29
     */
    public void send() {
        this.mailSender.send(this.message);
    }
}
