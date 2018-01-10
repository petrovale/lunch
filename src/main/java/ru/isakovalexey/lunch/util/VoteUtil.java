package ru.isakovalexey.lunch.util;

import org.springframework.http.HttpStatus;
import ru.isakovalexey.lunch.util.exception.ApplicationException;

import java.time.LocalTime;

public class VoteUtil {

    public static final String EXCEPTION_VOTING_RESTRICTION = "exception.vote.restrictionVote";

    private static LocalTime limitTime = LocalTime.of(11, 0);

    public static LocalTime getTime() {
        return limitTime;
    }

    public static void setTime(LocalTime limitTime) {
        VoteUtil.limitTime = limitTime;
    }

    public static void checkingTimeForSecondVote () {
        if (LocalTime.now().isAfter(VoteUtil.getTime())) {
            throw new ApplicationException(EXCEPTION_VOTING_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, VoteUtil.getTime().toString());
        }
    }
}
