package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.repository.RestaurantRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

import static ru.isakovalexey.lunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllWithVoicesByDate(Date dateVoice) {
        return restaurantRepository.getAllWithVoicesByDate(dateVoice);
    }

    @Override
    public List<Restaurant> getAllWithMealsByDate(Date date) {
        return restaurantRepository.getAllWithMealsByDate(date);
    }
}
