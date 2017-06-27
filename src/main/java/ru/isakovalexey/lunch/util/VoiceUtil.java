package ru.isakovalexey.lunch.util;

import java.time.LocalTime;

public class VoiceUtil {
    private static LocalTime limitTime = LocalTime.of(11, 0);

    public static LocalTime getTime() {
        return limitTime;
    }

    public static void setTime(LocalTime limitTime) {
        VoiceUtil.limitTime = limitTime;
    }

    public static boolean checkingTimeForSecondVote () {
        return LocalTime.now().isBefore(VoiceUtil.getTime());
    }
}
