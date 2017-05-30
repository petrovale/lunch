package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
public interface RestaurantService {
    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant update(Restaurant restaurant) throws NotFoundException;

    Restaurant save(Restaurant restaurant);
}
