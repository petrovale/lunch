package ru.isakovalexey.lunch.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.isakovalexey.lunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.MealTestData.*;
import static ru.isakovalexey.lunch.MealTestData.BLACK_THAI_ID;
import static ru.isakovalexey.lunch.MealTestData.BLACK_THAI_MEAL_ID;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.USER;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(BLACK_THAI_MEAL1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/meals/" + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(MealAdminRestController.REST_URL + '/' + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/lunch?date=" + "2017-05-30")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(BLACK_THAI_MEAL3, BLACK_THAI_MEAL2, BLACK_THAI_MEAL1));
    }
}