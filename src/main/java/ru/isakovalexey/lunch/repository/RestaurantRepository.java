package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Restaurant;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    List<Object[]> getAllWithVotesByDate(Date date);

    List<Restaurant> getAllWithMealsByDate(LocalDate date);
}
