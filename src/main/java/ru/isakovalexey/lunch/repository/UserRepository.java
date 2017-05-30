package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.User;

import java.util.List;

/**
 * Created by user on 30.05.2017.
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
