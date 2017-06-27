package ru.isakovalexey.lunch.repository;

import ru.isakovalexey.lunch.model.Voice;

import java.util.Date;

public interface VoiceRepository {
    // null if updated voice do not belong to restaurantId
    Voice save(Voice voice, int restaurantId, int userId);

    // null if voice do not belong to userId
    Voice get(Date date, int userId);
}
