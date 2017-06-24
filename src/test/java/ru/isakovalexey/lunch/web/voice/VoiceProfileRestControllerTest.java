package ru.isakovalexey.lunch.web.voice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.RestaurantTestData;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.web.AbstractControllerTest;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.USER;
import static ru.isakovalexey.lunch.VoiceTestData.MATCHER;

public class VoiceProfileRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = VoiceProfileRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void testBeforeCloseOfVoting() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        ResultActions action = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returned = MATCHER.fromJsonAction(action);
        Assert.notNull(returned, "voice not found");
    }

    @Test
    public void testAfterVotingIsClosed() throws Exception {
        mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnavailableForLegalReasons())
                .andExpect(content().json("{'cause':'ApplicationException'}"))
                .andDo(print());
    }

    @Test
    public void testReVote() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        ResultActions actionFirst = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returnedFirst = MATCHER.fromJsonAction(actionFirst);
        Assert.notNull(returnedFirst, "voice not found");

        ResultActions actionSecond = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returnedSecond = MATCHER.fromJsonAction(actionSecond);
        Assert.notNull(returnedSecond, "voice not found");

        BLACK_THAI.setVote(0);
        UGOLEK.setVote(0);
        WHITE_RABBIT.setVote(1);
        RestaurantTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(BLACK_THAI, WHITE_RABBIT, UGOLEK), service.getAllVoiceByDate(new Date()));
    }
}