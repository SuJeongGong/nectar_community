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
    /**
     * The Day time.
     */
    static SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddhhmmss");
    /**
     * The Date.
     */
    static SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");


    /**
     * description : 현재 날짜
     * methodName : getNow
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param type the type 형식지정
     * @return the now
     */
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

    /**
     * description : 현재날짜에서 원하는 기간만큼 계산하기
     * methodName : calculateDate
     * author : Gong SuJeong
     * date : 2022.06.09
     *
     * @param year
     * @param month
     * @param day
     * @return string
     */
    public static String calculateDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);

        return date.format(calendar.getTime());
    }
}
