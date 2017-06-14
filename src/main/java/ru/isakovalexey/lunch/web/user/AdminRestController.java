package ru.isakovalexey.lunch.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.service.UserService;

import java.util.List;

/**
 * Created by user on 31.05.2017.
 */
@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";

    @Autowired
    public AdminRestController(UserService service) {
        super(service);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }
}
