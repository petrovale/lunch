package ru.isakovalexey.lunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.isakovalexey.lunch.View;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.net.URI;

import static ru.isakovalexey.lunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController {
    private static final Logger log = LoggerFactory.getLogger(MealAdminRestController.class);
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private MealService service;

    @DeleteMapping("/{restaurantId}/meals/{mealId}")
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable(value = "mealId") int mealId) {
        log.info("delete meal {} for Restaurant {}", mealId, restaurantId);
        service.delete(mealId, restaurantId);
    }

    @PutMapping(value = "/{restaurantid}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.ValidatedREST.class) @RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        log.info("update {} with id={} for Restaurant {}", meal, meal.getId(), restaurantId);
        service.update(meal, restaurantId);
    }

    @PostMapping(value = "/{restaurantid}/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Validated(View.ValidatedREST.class) @RequestBody Meal meal, @PathVariable(value = "restaurantid") int restaurantId) {
        log.info("create {} for Restaurant {}", meal, restaurantId);
        checkNew(meal);
        Meal created = service.save(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
