package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.repository.MealRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by user on 31.05.2017.
 */
@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal get(int id, int restaurantId) throws NotFoundException {
        return mealRepository.get(id, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        mealRepository.delete(id, restaurantId);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return mealRepository.getAll(restaurantId);
    }

    @Override
    public Meal update(Meal meal, int restaurantId) throws NotFoundException {
        return mealRepository.save(meal, restaurantId);
    }

    @Override
    public Meal save(Meal meal, int restaurantId) {
        return mealRepository.save(meal, restaurantId);
    }
}
