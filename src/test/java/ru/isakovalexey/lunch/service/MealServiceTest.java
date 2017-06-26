package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.isakovalexey.lunch.MealTestData.*;

/**
 * Created by user on 13.06.2017.
 */
public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(BLACK_THAI_MEAL_ID, BLACK_THAI_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(BLACK_THAI_MEAL3, BLACK_THAI_MEAL2),
                service.getAll(BLACK_THAI_ID, getDate()));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(BLACK_THAI_MEAL_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.save(created, BLACK_THAI_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, BLACK_THAI_MEAL3, BLACK_THAI_MEAL2, BLACK_THAI_MEAL1), service.getAll(BLACK_THAI_ID, getDate()));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(UGOLEK_MEAL_ID + 2, UGOLEK_ID);
        MATCHER.assertEquals(UGOLEK_MEAL3, actual);
        System.out.println(actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(UGOLEK_MEAL_ID + 2, WHITE_RABBIT_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, BLACK_THAI_ID);
        MATCHER.assertEquals(updated, service.get(BLACK_THAI_MEAL_ID, BLACK_THAI_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(BLACK_THAI_MEAL1, BLACK_THAI_MEAL_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(WHITE_RABBIT_MEALS, service.getAll(WHITE_RABBIT_ID, getDate()));
    }
}
