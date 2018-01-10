package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.util.exception.ApplicationException;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static ru.isakovalexey.lunch.RestaurantTestData.UGOLEK_ID;
import static ru.isakovalexey.lunch.RestaurantTestData.WHITE_RABBIT_ID;
import static ru.isakovalexey.lunch.UserTestData.ADMIN_ID;
import static ru.isakovalexey.lunch.UserTestData.USER_ID;
import static ru.isakovalexey.lunch.VoiceTestData.*;

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
        assertMatch(service.get(date, USER_ID), newVoice);
    }

    @Test
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        VoiceUtil.setTime(LocalTime.now().plusHours(-1));
        Voice newVoice = service.voice(UGOLEK_ID, USER_ID);
        newVoice.setRestaurantId(UGOLEK_ID);
        newVoice.setUserId(USER_ID);
        assertMatch(service.get(date, USER_ID), newVoice);
    }

    @Test(expected = ApplicationException.class)
    public void testAfterClosingRepeatVoting() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(-1));

        service.voice(UGOLEK_ID, USER_ID);
        service.voice(UGOLEK_ID, USER_ID);
    }

    @Test
    public void testReVote() throws Exception {
        Date date = new Date();
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        service.voice(WHITE_RABBIT_ID, USER_ID);
        Voice secondVoice = service.voice(UGOLEK_ID, USER_ID);
        secondVoice.setRestaurantId(UGOLEK_ID);
        secondVoice.setUserId(USER_ID);

        assertMatch(service.get(date, USER_ID), secondVoice);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(new Date(), 1);
    }

    @Test
    public void testGet() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2017-06-20");
        Assert.notNull(service.get(date, ADMIN_ID), "voice not found");
    }
}
