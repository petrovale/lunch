package ru.isakovalexey.lunch.util;

import ru.isakovalexey.lunch.model.Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 16.06.2017.
 */
public class RestaurantUtil {

    public static List<Restaurant> getWithVoice(List<Object[]> objects) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (Object[] object : objects) {
            int id = ((Number) object[0]).intValue();
            String name = (String) object[1];
            Date registered = (Date) object[2];
            int vote = ((Number) object[3]).intValue();
            restaurants.add(new Restaurant(id, name, vote, registered));
        }

        return restaurants;
    }

}
