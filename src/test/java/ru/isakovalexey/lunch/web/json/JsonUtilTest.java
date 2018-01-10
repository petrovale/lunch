package ru.isakovalexey.lunch.web.json;

import org.junit.Test;
import ru.isakovalexey.lunch.model.Meal;

import java.util.List;

import static ru.isakovalexey.lunch.MealTestData.*;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(UGOLEK_MEAL3);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, UGOLEK_MEAL3);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(WHITE_RABBIT_MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, WHITE_RABBIT_MEALS);
    }
}
