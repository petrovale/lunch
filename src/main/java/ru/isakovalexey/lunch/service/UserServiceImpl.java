package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.isakovalexey.lunch.AuthorizedUser;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.repository.UserRepository;
import ru.isakovalexey.lunch.to.UserTo;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.List;

import static ru.isakovalexey.lunch.util.UserUtil.prepareToSave;
import static ru.isakovalexey.lunch.util.UserUtil.updateFromTo;
import static ru.isakovalexey.lunch.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by user on 31.05.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
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
        Assert.notNull(user, "user must not be null");
        userRepository.save(prepareToSave(user));
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(prepareToSave(user));
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
