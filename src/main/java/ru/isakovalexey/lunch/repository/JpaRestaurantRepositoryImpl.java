package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import ru.isakovalexey.lunch.model.Restaurant;

import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
@Repository
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
