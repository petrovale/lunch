package ru.isakovalexey.lunch.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.isakovalexey.lunch.TestUtil;
import ru.isakovalexey.lunch.model.Role;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.web.AbstractControllerTest;
import ru.isakovalexey.lunch.web.json.JsonUtil;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.isakovalexey.lunch.UserTestData.*;
import static ru.isakovalexey.lunch.web.user.ProfileRestController.REST_URL;

/**
 * Created by user on 14.06.2017.
 */
public class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, new User(userService.getByEmail("newemail@ya")));
    }
}
