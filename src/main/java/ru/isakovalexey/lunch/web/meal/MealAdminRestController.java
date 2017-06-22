package ru.isakovalexey.lunch.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController extends AbstractMealController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    public MealAdminRestController(MealService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/{restaurantId}/meals/{mealId}")
    public Meal get(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        return super.get(mealId, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/meals/{mealId}")
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        super.delete(mealId, restaurantId);
    }

    @Override
    @GetMapping(value = "/{restaurantid}/lunch")
    public List<Meal> getAll(@PathVariable(value = "restaurantid") int restaurantId,
                             @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return super.getAll(restaurantId, date);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        super.update(meal, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
