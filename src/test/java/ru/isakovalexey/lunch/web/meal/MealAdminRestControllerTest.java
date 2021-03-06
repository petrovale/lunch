package ru.isakovalexey.lunch.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;
import ru.isakovalexey.lunch.web.AbstractControllerTest;
import ru.isakovalexey.lunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.MealTestData.*;
import static ru.isakovalexey.lunch.TestUtil.userHttpBasic;
import static ru.isakovalexey.lunch.UserTestData.ADMIN;
import static ru.isakovalexey.lunch.UserTestData.USER;

public class MealAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealAdminRestController.REST_URL + '/';

    @Autowired
    private MealService service;

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + BLACK_THAI_ID + "/meals/" + BLACK_THAI_MEAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(service.getAll(BLACK_THAI_ID, getDate()), BLACK_THAI_MEAL3, BLACK_THAI_MEAL2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + BLACK_THAI_ID + "/meals/" + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        mockMvc.perform(put(REST_URL + BLACK_THAI_ID + "/meals")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(service.get(updated.getId(), BLACK_THAI_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + BLACK_THAI_ID + "/meals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Meal returned = TestUtil.readFromJson(action, Meal.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(BLACK_THAI_ID, getDate()), created, BLACK_THAI_MEAL3, BLACK_THAI_MEAL2, BLACK_THAI_MEAL1);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Meal invalid = new Meal(null, null);
        mockMvc.perform(post(REST_URL + BLACK_THAI_ID + "/meals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{'cause':'ValidationException'}"))
                .andDo(print());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Meal invalid = new Meal(BLACK_THAI_MEAL_ID, null, null, getDate());
        mockMvc.perform(put(REST_URL + BLACK_THAI_ID + "/meals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{'cause':'ValidationException'}"))
                .andDo(print());
    }
}