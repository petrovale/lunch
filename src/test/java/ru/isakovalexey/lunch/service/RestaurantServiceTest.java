package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.*;

import static ru.isakovalexey.lunch.RestaurantTestData.*;

/**
 * Created by user on 17.06.2017.
 */
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New", 0);
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(BLACK_THAI, newRestaurant, WHITE_RABBIT, UGOLEK), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        service.save(new Restaurant(null, "Black Thai", 0));
    }

    @Test
    public void testGetAllVoices() throws Exception {
        List<Restaurant> all = service.getAllVoice();
        BLACK_THAI.setVote(0);
        WHITE_RABBIT.setVote(1);
        UGOLEK.setVote(2);
        MATCHER.assertCollectionEquals(Arrays.asList(BLACK_THAI, WHITE_RABBIT, UGOLEK), all);
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(BLACK_THAI_ID);
        MATCHER.assertEquals(BLACK_THAI, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAllVoicesByDate() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2017-06-18");

        List<Restaurant> all = service.getAllVoiceByDate(date);
        WHITE_RABBIT.setVote(1);
        UGOLEK.setVote(1);
        MATCHER.assertCollectionEquals(Arrays.asList(BLACK_THAI, WHITE_RABBIT, UGOLEK), all);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        System.out.println("actual " + all.toString());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(WHITE_RABBIT);
        updated.setName("UpdatedName");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(WHITE_RABBIT_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BLACK_THAI_ID);
        WHITE_RABBIT.setVote(0);
        UGOLEK.setVote(0);
        MATCHER.assertCollectionEquals(Arrays.asList(WHITE_RABBIT, UGOLEK), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }
}
