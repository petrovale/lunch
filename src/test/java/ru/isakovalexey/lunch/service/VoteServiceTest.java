package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.model.Vote;
import ru.isakovalexey.lunch.util.VoteUtil;
import ru.isakovalexey.lunch.util.exception.ApplicationException;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static ru.isakovalexey.lunch.RestaurantTestData.UGOLEK_ID;
import static ru.isakovalexey.lunch.RestaurantTestData.WHITE_RABBIT_ID;
import static ru.isakovalexey.lunch.UserTestData.ADMIN_ID;
import static ru.isakovalexey.lunch.UserTestData.USER_ID;
import static ru.isakovalexey.lunch.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void testBeforeCloseOfVoting() throws Exception {
        Date date = new Date();

        VoteUtil.setTime(LocalTime.now().plusHours(1));
        Vote newVote = service.vote(UGOLEK_ID, USER_ID);
        newVote.setRestaurantId(UGOLEK_ID);
        newVote.setUserId(USER_ID);
        assertMatch(service.get(date, USER_ID), newVote);
    }

    @Test
    public void testAfterVotingIsClosed() throws Exception {
        Date date = new Date();

        VoteUtil.setTime(LocalTime.now().plusHours(-1));
        Vote newVote = service.vote(UGOLEK_ID, USER_ID);
        newVote.setRestaurantId(UGOLEK_ID);
        newVote.setUserId(USER_ID);
        assertMatch(service.get(date, USER_ID), newVote);
    }

    @Test(expected = ApplicationException.class)
    public void testAfterClosingRepeatVoting() throws Exception {
        VoteUtil.setTime(LocalTime.now().plusHours(-1));

        service.vote(UGOLEK_ID, USER_ID);
        service.vote(UGOLEK_ID, USER_ID);
    }

    @Test
    public void testReVote() throws Exception {
        Date date = new Date();
        VoteUtil.setTime(LocalTime.now().plusHours(1));

        service.vote(WHITE_RABBIT_ID, USER_ID);
        Vote secondVote = service.vote(UGOLEK_ID, USER_ID);
        secondVote.setRestaurantId(UGOLEK_ID);
        secondVote.setUserId(USER_ID);

        assertMatch(service.get(date, USER_ID), secondVote);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(new Date(), 1);
    }

    @Test
    public void testGet() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2017-06-20");
        Assert.notNull(service.get(date, ADMIN_ID), "vote not found");
    }
}
