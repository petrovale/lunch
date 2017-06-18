package ru.isakovalexey.lunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;

import java.util.Date;
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

    public List<Restaurant> getAllVoice() {
        log.info("getAllVoice");
        return service.getAllVoice();
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

    public void voice(int id, boolean voice) {
        log.info((voice ? "add voice " : "remove the voice ") + id);
        service.voice(id, voice);
    }

    public List<Restaurant> getAllVoiceByDate(Date dateVoice) {
        log.info("getAllVoiceByDate {}", dateVoice);
        return service.getAllVoiceByDate(dateVoice);
    }
}
