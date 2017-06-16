package ru.isakovalexey.lunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.util.List;

import static ru.isakovalexey.lunch.util.ValidationUtil.*;

/**
 * Created by user on 15.06.2017.
 */
public abstract class AbstractMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMealController.class);

    private final MealService service;

    public AbstractMealController(MealService service) {
        this.service = service;
    }

    public Meal get(int id, int restaurantId) {
        LOG.info("get meal {} for Restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        LOG.info("delete meal {} for Restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }


    public List<Meal> getAll(int restaurantId) {
        LOG.info("getAll for Restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public void update(Meal meal, int restaurantId) {
        LOG.info("update {} with id={} for Restaurant {}", meal, meal.getId(), restaurantId);
        service.update(meal, restaurantId);
    }

    public Meal create(Meal meal, int restaurantId) {
        LOG.info("create {} for Restaurant {}", meal, restaurantId);
        checkNew(meal);
        return service.save(meal, restaurantId);
    }
}