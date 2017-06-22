package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.Date;

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
        Voice newVoice = service.voice(100004, 100000);
        newVoice.setRestaurantId(100004);
        newVoice.setUserId(100000);
        MATCHER.assertEquals(newVoice, service.get(date, 100000));
    }

    @Test(expected = NotFoundException.class)
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        VoiceUtil.setTime(LocalTime.now().plusHours(-1));
        service.voice(100004, 100000);
        service.get(date, 100000);
    }

    @Test
    public void testReVote() throws Exception {
        Date date = new Date();
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        service.voice(100003, 100000);
        Voice secondVoice = service.voice(100004, 100000);
        secondVoice.setRestaurantId(100004);
        secondVoice.setUserId(100000);

        MATCHER.assertEquals(secondVoice, service.get(date, 100000));
    }

}
