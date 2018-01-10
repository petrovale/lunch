package ru.isakovalexey.lunch.web.voice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.RestaurantTestData;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.web.AbstractControllerTest;

import java.time.LocalTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.USER;

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

        Voice returned = TestUtil.readFromJson(action, Voice.class);
        Assert.notNull(returned, "voice not found");
    }

    @Test
    public void testAfterClosingRepeatVoting() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(-1));

        mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnavailableForLegalReasons())
                .andExpect(content().json("{'cause':'ApplicationException'}"))
                .andDo(print());
    }

    @Test
    public void testAfterVotingIsClosed() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(-1));

        ResultActions action = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returned = TestUtil.readFromJson(action, Voice.class);
        Assert.notNull(returned, "voice not found");
    }

    @Test
    public void testReVote() throws Exception {
        VoiceUtil.setTime(LocalTime.now().plusHours(1));

        ResultActions actionFirst = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returnedFirst = TestUtil.readFromJson(actionFirst, Voice.class);
        Assert.notNull(returnedFirst, "voice not found");

        ResultActions actionSecond = mockMvc.perform(post(REST_URL + WHITE_RABBIT_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        Voice returnedSecond = TestUtil.readFromJson(actionSecond, Voice.class);
        Assert.notNull(returnedSecond, "voice not found");

        BLACK_THAI_TO.setVote(0);
        UGOLEK_TO.setVote(0);
        WHITE_RABBIT_TO.setVote(1);
        RestaurantTestData.assertMatchTo(service.getAllWithVoicesByDate(new Date()), BLACK_THAI_TO, WHITE_RABBIT_TO, UGOLEK_TO);
    }
}