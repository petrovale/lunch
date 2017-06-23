package ru.isakovalexey.lunch.web.restaurant;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.USER;

/**
 * Created by user on 23.06.2017.
 */
public class RestaurantProfileRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = RestaurantProfileRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL  + BLACK_THAI_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(BLACK_THAI)));
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
        BLACK_THAI.setVote(0);
        WHITE_RABBIT.setVote(1);
        UGOLEK.setVote(2);
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(BLACK_THAI, WHITE_RABBIT, UGOLEK)));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        BLACK_THAI.setVote(0);
        WHITE_RABBIT.setVote(1);
        UGOLEK.setVote(1);
        TestUtil.print(mockMvc.perform(get(REST_URL + "by?date=" + "2017-06-18")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(BLACK_THAI, WHITE_RABBIT, UGOLEK)));
    }
}
