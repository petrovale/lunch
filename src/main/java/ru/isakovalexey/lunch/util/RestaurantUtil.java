package ru.isakovalexey.lunch.util;

import ru.isakovalexey.lunch.model.Restaurant;

/**
 * Created by user on 16.06.2017.
 */
public class RestaurantUtil {

    public static Restaurant updateVoice(Restaurant restaurant, boolean voice) {
        int voiceRestaurant = restaurant.getVote();

        if (voice) {
            restaurant.setVote(voiceRestaurant + 1);

        } else {
            restaurant.setVote(voiceRestaurant - 1);
        }
        return restaurant;
    }
}
