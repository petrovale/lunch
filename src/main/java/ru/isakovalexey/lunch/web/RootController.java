package ru.isakovalexey.lunch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.isakovalexey.lunch.service.RestaurantService;
import ru.isakovalexey.lunch.service.UserService;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String restaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllVoice());
        return "restaurants";
    }
}
