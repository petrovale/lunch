package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Restaurant;

import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
