package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.repository.VoiceRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

@Service
public class VoiceServiceImpl implements VoiceService {

    private final VoiceRepository repository;

    @Autowired
    public VoiceServiceImpl(VoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Voice save(Voice voice, int restaurantId, int userId) {
        return repository.save(voice, restaurantId, userId);
    }

    @Override
    public Voice get(Date date, int userId) throws NotFoundException {
        return repository.get(date, userId);
    }

    @Override
    public Voice voice(int restaurantId, boolean voice, int userId) {
        return repository.voice(restaurantId, voice, userId);
    }
}
