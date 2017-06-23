package ru.isakovalexey.lunch.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.web.AbstractControllerTest;
import ru.isakovalexey.lunch.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.RestaurantTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.ADMIN;
import static ru.isakovalexey.lunch.UserTestData.USER;

/**
 * Created by user on 22.06.2017.
 */
public class RestaurantAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantAdminRestController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(BLACK_THAI));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + BLACK_THAI_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(WHITE_RABBIT, UGOLEK), restaurantService.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(BLACK_THAI);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + BLACK_THAI_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, restaurantService.get(BLACK_THAI_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "New", 0);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Restaurant returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(BLACK_THAI, expected, WHITE_RABBIT, UGOLEK), restaurantService.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        BLACK_THAI.setVote(0);
        WHITE_RABBIT.setVote(1);
        UGOLEK.setVote(2);
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(BLACK_THAI, WHITE_RABBIT, UGOLEK)));
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Restaurant updated = new Restaurant(BLACK_THAI);
        updated.setName("");
        mockMvc.perform(put(REST_URL + BLACK_THAI_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print())
                .andExpect(content().json("{'cause':'ValidationException'}"))
                .andDo(print());
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Restaurant expected = new Restaurant(null, "", 0);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{'cause':'ValidationException'}"))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        Restaurant updated = new Restaurant(BLACK_THAI);
        updated.setName("White Rabbit");
        mockMvc.perform(put(REST_URL + BLACK_THAI_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        Restaurant expected = new Restaurant(null, "White Rabbit", 0);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isConflict());

    }
}