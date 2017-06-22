package ru.isakovalexey.lunch.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = MealProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealProfileRestController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    public MealProfileRestController(MealService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/{restaurantId}/meals/{mealId}")
    public Meal get(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        return super.get(mealId, restaurantId);
    }

    @Override
    @GetMapping(value = "/{restaurantid}/lunch")
    public List<Meal> getAll(@PathVariable(value = "restaurantid") int restaurantId,
                             @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return super.getAll(restaurantId, date);
    }
}
