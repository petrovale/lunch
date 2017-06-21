package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.matcher.ModelMatcher;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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
        service.voice(100004, true, 100000);
        service.get(date, 100000);
    }

    @Test(expected = NotFoundException.class)
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        service.voice(100004, true, 100000);
        service.get(date, 100000);
    }
}
