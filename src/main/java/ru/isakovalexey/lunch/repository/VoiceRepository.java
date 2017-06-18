package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.model.Voice;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 17.06.2017.
 */
public interface VoiceRepository {
    // null if updated voice do not belong to restaurantId
    Voice save(Voice voice, int restaurantId, int userId);

    // false if voice do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if voice do not belong to userId
    Voice get(Date dateVoice, int userId);

    // ORDERED dateTime
    List<Voice> getAll(int restaurantId);
}
