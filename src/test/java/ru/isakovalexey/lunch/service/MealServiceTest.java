package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Date;

import static ru.isakovalexey.lunch.MealTestData.*;

/**
 * Created by user on 13.06.2017.
 */
public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(Black_Thai_MEAL_ID, BLACK_THAI_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(Black_Thai_MEAL3, Black_Thai_MEAL2),
                service.getAll(BLACK_THAI_ID, new Date()));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(Black_Thai_MEAL_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.save(created, UGOLEK_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, Ugolek_MEAL3, Ugolek_MEAL2, Ugolek_MEAL1), service.getAll(UGOLEK_ID, new Date()));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(Ugolek_MEAL_ID + 2, UGOLEK_ID);
        MATCHER.assertEquals(Ugolek_MEAL3, actual);
        System.out.println(actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(Ugolek_MEAL_ID + 2, WHITE_RABBIT_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, BLACK_THAI_ID);
        MATCHER.assertEquals(updated, service.get(Black_Thai_MEAL_ID, BLACK_THAI_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + Black_Thai_MEAL_ID);
        service.update(Black_Thai_MEAL1, White_Rabbit_MEAL_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(WHITE_RABBIT_MEALS, service.getAll(WHITE_RABBIT_ID, new Date()));
    }
}
