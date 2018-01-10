package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.repository.RestaurantRepository;
import ru.isakovalexey.lunch.to.RestaurantTo;
import ru.isakovalexey.lunch.util.RestaurantUtil;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.time.LocalDate;
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

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Cacheable("restaurants")
    @Override
    public List<RestaurantTo> getAllWithVotesByDate(Date date) {
        return RestaurantUtil.createWithVote(restaurantRepository.getAllWithVotesByDate(date));
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAllWithMealsByDate(LocalDate date) {
        return restaurantRepository.getAllWithMealsByDate(date);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }
}
