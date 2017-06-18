package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Voice;

import java.text.SimpleDateFormat;
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
        Voice created = new Voice();
        service.save(created, 100004, 100001);

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        Voice actual = service.get(date1, 100001);
        System.out.println("actual " + actual);
    }
}
