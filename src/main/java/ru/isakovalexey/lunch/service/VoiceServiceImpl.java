package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.repository.VoiceRepository;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

import static ru.isakovalexey.lunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiceServiceImpl implements VoiceService {

    private final VoiceRepository repository;

    @Autowired
    public VoiceServiceImpl(VoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Voice get(Date date, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(date, userId), userId);
    }

    @Override
    @Transactional
    public Voice voice(int restaurantId, int userId) {
        Date currentDate = new Date();
        return repository.save(currentDate, restaurantId, userId);
    }
}
