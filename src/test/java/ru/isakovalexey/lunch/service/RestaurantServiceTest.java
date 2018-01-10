package ru.isakovalexey.lunch.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.MealTestData;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.to.RestaurantTo;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.*;

import static ru.isakovalexey.lunch.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant("New");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), BLACK_THAI, newRestaurant, WHITE_RABBIT, UGOLEK);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        service.save(new Restaurant(null, "Black Thai"));
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(BLACK_THAI_ID);
        assertMatch(restaurant, BLACK_THAI);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAllVoicesByDate() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2017-06-18");

        List<RestaurantTo> all = service.getAllWithVotesByDate(date);
        WHITE_RABBIT_TO.setVote(1);
        UGOLEK_TO.setVote(1);
        assertMatchTo(all, BLACK_THAI_TO, WHITE_RABBIT_TO, UGOLEK_TO);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        System.out.println("actual " + all.toString());
        Assert.notNull(all, "restaurants must not be null");
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(WHITE_RABBIT);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(WHITE_RABBIT_ID), updated);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BLACK_THAI_ID);
        assertMatch(service.getAll(), WHITE_RABBIT, UGOLEK);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGetAllWithMealsByDate() throws Exception {
        List<Restaurant> all = service.getAllWithMealsByDate(MealTestData.getDate());
        Assert.notNull(all, "restaurants must not be null");
        System.out.println("actual " + all.toString());
    }
}
