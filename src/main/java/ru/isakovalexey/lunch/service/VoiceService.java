package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

/**
 * Created by user on 17.06.2017.
 */
public interface VoiceService {

    Voice get(Date date, int userId) throws NotFoundException;

    Voice voice(int restaurantId, int userId);
}
