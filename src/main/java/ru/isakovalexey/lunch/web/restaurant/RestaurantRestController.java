package ru.isakovalexey.lunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.to.RestaurantTo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL)
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @GetMapping(value = "/lunches/by-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMealsByDate(@RequestParam("date")
                                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Restaurant> restaurants = this.service.getAllWithMealsByDate(date);
        return restaurants;
    }

    @GetMapping(value = "/votes/by-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAllWithVoicesByDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        log.info("getAllVoiceByDate {}", date);
        return service.getAllWithVoicesByDate(date);
    }
}
