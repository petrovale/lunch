package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Vote;

import java.util.Date;

public interface VoteRepository {
    // null if updated voice do not belong to restaurantId
    Vote save(Date date, int restaurantId, int userId);

    // null if voice do not belong to userId
    Vote get(Date date, int userId);
}
