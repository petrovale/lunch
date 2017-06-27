package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    Meal get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Meal> getAll(int restaurantId, LocalDate date);

    Meal update(Meal meal, int restaurantId) throws NotFoundException;

    Meal save(Meal meal, int restaurantId);
}
