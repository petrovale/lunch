package ru.isakovalexey.lunch.util;

import ru.isakovalexey.lunch.to.RestaurantTo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantUtil {

    public static List<RestaurantTo> createWithVote(List<Object[]> objects) {
       List<RestaurantTo> restaurantsTo = new ArrayList<>();

        objects.forEach(obj -> {
            restaurantsTo.add(new RestaurantTo(
                    ((Number) obj[0]).intValue(),
                    (String) obj[1],
                    ((Number) obj[3]).intValue(),
                    LocalDateTime.ofInstant(((Date) obj[2]).toInstant(), ZoneId.systemDefault())));});

        return restaurantsTo;
    }

}
