package ru.isakovalexey.lunch.web.restaurant;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.USER;

public class RestaurantRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL  + BLACK_THAI_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BLACK_THAI)));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAll() throws Exception {
        //BLACK_THAI.setVote(0);
       // WHITE_RABBIT.setVote(0);
        //UGOLEK.setVote(0);
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BLACK_THAI, WHITE_RABBIT, UGOLEK)));
    }

    @Test
    public void testGetAllWithVoicesByDate() throws Exception {
        BLACK_THAI_TO.setVote(0);
        WHITE_RABBIT_TO.setVote(1);
        UGOLEK_TO.setVote(1);
        TestUtil.print(mockMvc.perform(get(REST_URL + "votes/by-date?date=" + "2017-06-18")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BLACK_THAI_TO, WHITE_RABBIT_TO, UGOLEK_TO)));
    }

    @Test
    public void testGetAllWithMealsByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/lunches/by-date?date=" + "2017-05-30")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
