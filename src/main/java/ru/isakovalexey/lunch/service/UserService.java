package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.to.UserTo;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void update(UserTo user);
}
