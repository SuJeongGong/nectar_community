package kr.co.nectarsoft.community.common.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * packageName      : kr.co.nectarsoft.community.common
 * date             : 2022-05-29
 * author           : SuJeong Gong
 * version	        : 1.0
 * description      : sha-256 암호화 관련 클래스
 * ================================================================
 * DATE             AUTHOR              NOTE
 * ----------------------------------------------------------------
 * 2022-05-29       SuJeong Gong        최초작성
 */
@Component
public class SHA256 {
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
