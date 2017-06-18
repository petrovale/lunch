package ru.isakovalexey.lunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.isakovalexey.lunch.model.Restaurant;

import java.util.Collection;
import java.util.List;

/**
 * Created by user on 17.06.2017.
 */
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testGetAllVoices() throws Exception {
        List<Restaurant> all = service.getAllVoice();
        System.out.println("actual " + all.toString());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> all = service.getAll();
        System.out.println("actual " + all.toString());
    }
}
