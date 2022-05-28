package kr.co.nectarsoft.community.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * packageName    : kr.co.nectarsoft.community.common
 * fileName       : DateUtils
 * author         : GongSuJeong
 * date           : 2022-05-27
 * description    : 날짜, 시간 관련 모음 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-27        GongSuJeong       최초 생성
 */
public class DateUtils {
    static SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    static SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");

    public static String getNow(String type) {
        Calendar calendar = Calendar.getInstance();
        String result = "";

        if("date".equals(type)){
            result = date.format(calendar.getTime());
        } else if ("dateTime".equals(type)) {
            result = dayTime.format(calendar.getTime());
        }

        return result;
    }
}
