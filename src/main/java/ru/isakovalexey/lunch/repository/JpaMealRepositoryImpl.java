package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import ru.isakovalexey.lunch.model.Meal;

import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @Override
    public Meal save(Meal meal, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return null;
    }
}
