package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Vote;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

public interface VoteService {

    Vote get(Date date, int userId) throws NotFoundException;

    Vote vote(int restaurantId, int userId);
}
