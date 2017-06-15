package ru.isakovalexey.lunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;

import java.util.List;

import static ru.isakovalexey.lunch.util.ValidationUtil.*;

/**
 * Created by user on 15.06.2017.
 */
public abstract class AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    private final RestaurantService service;

    public AbstractRestaurantController(RestaurantService service) {
        this.service = service;
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        checkIdConsistent(restaurant, id);
        service.update(restaurant);
    }
}
