package ru.isakovalexey.lunch.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.service.RestaurantService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL)
public class RestaurantProfileRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    public RestaurantProfileRestController(RestaurantService service) {
        super(service);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllVoice() {
        return super.getAllVoice();
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllVoiceByDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return super.getAllVoiceByDate(date);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }
}
