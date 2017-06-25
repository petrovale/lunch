package ru.isakovalexey.lunch.service;

import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

public interface VoiceService {

    Voice get(Date date, int userId) throws NotFoundException;

    Voice voice(int restaurantId, int userId);
}
