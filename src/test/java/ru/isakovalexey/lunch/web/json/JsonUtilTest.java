package ru.isakovalexey.lunch.web.json;

import org.junit.Test;
import ru.isakovalexey.lunch.MealTestData;
import ru.isakovalexey.lunch.model.Meal;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.UGOLEK_MEAL3);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        MealTestData.MATCHER.assertEquals(MealTestData.UGOLEK_MEAL3, meal);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.WHITE_RABBIT_MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.WHITE_RABBIT_MEALS, meals);
    }
}
