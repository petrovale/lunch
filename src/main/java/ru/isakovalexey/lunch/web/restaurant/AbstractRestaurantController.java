package ru.isakovalexey.lunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.to.RestaurantTo;
import ru.isakovalexey.lunch.util.exception.ErrorInfo;
import ru.isakovalexey.lunch.web.ExceptionInfoHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static ru.isakovalexey.lunch.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    public static final String EXCEPTION_DUPLICATE_NAME = "exception.restaurant.duplicateName";

    protected final RestaurantService service;

    @Autowired
    private ExceptionInfoHandler exceptionInfoHandler;

    public AbstractRestaurantController(RestaurantService service) {
        this.service = service;
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

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<RestaurantTo> getAllWithVoicesByDate(Date date) {
        log.info("getAllVoiceByDate {}", date);
        return service.getAllWithVoicesByDate(date);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
        return exceptionInfoHandler.getErrorInfoResponseEntity(req, e, EXCEPTION_DUPLICATE_NAME, HttpStatus.CONFLICT);
    }
}
