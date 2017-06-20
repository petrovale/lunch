package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Voice;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 17.06.2017.
 */
public class VoiceServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoiceService service;

    @Test
    public void testSave() throws Exception {
        Voice created = new Voice();
        service.save(created, 100004, 100001);
        Voice created1 = new Voice();
        service.save(created1, 100004, 100001);
        Voice created2 = new Voice();
        service.save(created2, 100004, 100001);
    }

    @Test
    public void testGet() throws Exception {
        Date date = new Date();

        Voice actual = service.get(date, 100000);
        System.out.println("actual " + actual);
    }

    @Test
    public void testVoice() throws Exception {
        /*
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date d = cal.getTime();
        System.out.println(d);
        */

        Voice voice = service.voice(100004, true, 100000);
        System.out.println(voice);
    }
}
