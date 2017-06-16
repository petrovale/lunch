package ru.isakovalexey.lunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.service.MealService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController extends AbstractMealController {
    static final String REST_URL = "/rest/admin/meals";

    @Autowired
    public MealAdminRestController(MealService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Meal get(@PathVariable("id") int id, @RequestParam(value = "restaurantid") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @RequestParam(value = "restaurantid") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    @GetMapping(value = "/restaurant")
    public List<Meal> getAll(@RequestParam(value = "restaurantid") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @PutMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @RequestParam(value = "restaurantid") int restaurantId) {
        super.update(meal, restaurantId);
    }

    @PostMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @RequestParam(value = "restaurantid") int restaurantId) {
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}