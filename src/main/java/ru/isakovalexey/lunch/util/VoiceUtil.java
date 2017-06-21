package ru.isakovalexey.lunch.util;

import java.time.LocalTime;

/**
 * Created by user on 21.06.2017.
 */
public class VoiceUtil {
    private static LocalTime limitTime = LocalTime.of(11, 0);

    public static LocalTime getTime() {
        return limitTime;
    }

    public static void setTime(LocalTime limitTime) {
        VoiceUtil.limitTime = limitTime;
    }

}
