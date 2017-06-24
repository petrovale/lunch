package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.util.exception.ApplicationException;

import java.time.LocalTime;
import java.util.Date;

import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.UserTestData.USER_ID;
import static ru.isakovalexey.lunch.VoiceTestData.MATCHER;

/**
 * Created by user on 17.06.2017.
 */
public class VoiceServiceTest extends AbstractServiceTest {

    @Autowired
    private VoiceService service;

    @Test
    public void testBeforeCloseOfVoting() throws Exception {
        Date date = new Date();

        VoiceUtil.setTime(LocalTime.now().plusHours(1));
        Voice newVoice = service.voice(UGOLEK_ID, USER_ID);
        newVoice.setRestaurantId(UGOLEK_ID);
        newVoice.setUserId(USER_ID);
        MATCHER.assertEquals(newVoice, service.get(date, USER_ID));
    }

    @Test(expected = ApplicationException.class)
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        VoiceUtil.setTime(LocalTime.now().plusHours(-1));
        service.voice(UGOLEK_ID, USER_ID);
        service.get(date, USER_ID);
    }

    @Test
    public void testReVote() throws Exception {
        Date date = new Date();
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        service.voice(WHITE_RABBIT_ID, USER_ID);
        Voice secondVoice = service.voice(UGOLEK_ID, USER_ID);
        secondVoice.setRestaurantId(UGOLEK_ID);
        secondVoice.setUserId(USER_ID);

        MATCHER.assertEquals(secondVoice, service.get(date, USER_ID));
    }

}
