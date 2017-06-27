package ru.isakovalexey.lunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private MealService service;

    @GetMapping(value = "/{restaurantId}/meals/{mealId}")
    public Meal get(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        log.info("get meal {} for Restaurant {}", mealId, restaurantId);
        return service.get(mealId, restaurantId);
    }

    @GetMapping(value = "/{restaurantid}/lunch")
    public List<Meal> getAll(@PathVariable(value = "restaurantid") int restaurantId,
                             @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        log.info("getAll for Restaurant {}", restaurantId);
        return service.getAll(restaurantId, date);
    }
}
