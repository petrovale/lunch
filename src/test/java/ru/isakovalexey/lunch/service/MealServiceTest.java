package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import static ru.isakovalexey.lunch.MealTestData.*;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(BLACK_THAI_MEAL_ID, BLACK_THAI_ID);
        assertMatch(service.getAll(BLACK_THAI_ID, getDate()), BLACK_THAI_MEAL3, BLACK_THAI_MEAL2);
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
        assertMatch(service.getAll(BLACK_THAI_ID, getDate()), created, BLACK_THAI_MEAL3, BLACK_THAI_MEAL2, BLACK_THAI_MEAL1);
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = service.get(UGOLEK_MEAL_ID + 2, UGOLEK_ID);
        assertMatch(actual, UGOLEK_MEAL3);
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
        assertMatch(service.get(updated.getId(), BLACK_THAI_ID), updated);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(BLACK_THAI_MEAL1, BLACK_THAI_MEAL_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        assertMatch(service.getAll(WHITE_RABBIT_ID, getDate()), WHITE_RABBIT_MEALS);
    }
}
