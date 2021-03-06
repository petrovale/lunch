package ru.isakovalexey.lunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.service.UserService;
import ru.isakovalexey.lunch.to.UserTo;

import java.util.List;

import static ru.isakovalexey.lunch.util.ValidationUtil.checkIdConsistent;
import static ru.isakovalexey.lunch.util.ValidationUtil.checkNew;

public class AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserController.class);

    private final UserService service;

    public AbstractUserController(UserService service) {
        this.service = service;
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        checkIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        checkIdConsistent(userTo, id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
