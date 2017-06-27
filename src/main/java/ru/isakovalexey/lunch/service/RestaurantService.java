package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface RestaurantService {
    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant update(Restaurant restaurant) throws NotFoundException;

    Restaurant save(Restaurant restaurant);

    List<Restaurant> getAllWithVoicesByDate(Date dateVoice);

    List<Restaurant> getAllWithMealsByDate(Date date);
}
