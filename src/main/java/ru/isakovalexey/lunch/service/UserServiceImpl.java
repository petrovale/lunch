package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.repository.UserRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by user on 31.05.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        userRepository.delete(id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return userRepository.get(id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
