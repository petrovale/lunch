package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.isakovalexey.lunch.model.Vote;
import ru.isakovalexey.lunch.repository.VoteRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

import static ru.isakovalexey.lunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote get(Date date, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(date, userId), userId);
    }

    @Override
    public Vote vote(int restaurantId, int userId) {
        Date currentDate = new Date();
        return repository.save(currentDate, restaurantId, userId);
    }
}
