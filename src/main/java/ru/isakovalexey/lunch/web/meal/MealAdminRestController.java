package ru.isakovalexey.lunch.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.isakovalexey.lunch.View;
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
    @DeleteMapping("/{restaurantId}/meals/{mealId}")
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        super.delete(mealId, restaurantId);
    }

    @Override
    @PutMapping(value = "/{restaurantid}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedREST.class) @RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        super.update(meal, restaurantId);
    }

    @PostMapping(value = "/{restaurantid}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.ValidatedREST.class) @RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
