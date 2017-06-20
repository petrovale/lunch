package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Meal;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
public interface MealRepository {
    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime
    List<Meal> getAll(int restaurantId, Date date);
}
