package ru.isakovalexey.lunch.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.util.List;

@RestController
@RequestMapping(value = MealProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealProfileRestController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/meals";

    @Autowired
    public MealProfileRestController(MealService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Meal get(@PathVariable("id") int id, @RequestParam(value = "restaurantid") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @GetMapping(value = "/restaurant")
    public List<Meal> getAll(@RequestParam(value = "restaurantid") int restaurantId) {
        return super.getAll(restaurantId);
    }
}
