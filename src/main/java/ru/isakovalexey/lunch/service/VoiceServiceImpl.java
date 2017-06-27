package ru.isakovalexey.lunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Voice;
import ru.isakovalexey.lunch.repository.VoiceRepository;
import ru.isakovalexey.lunch.util.VoiceUtil;
import ru.isakovalexey.lunch.util.exception.ApplicationException;
import ru.isakovalexey.lunch.util.exception.NotFoundException;

import java.util.Date;

import static java.util.Objects.isNull;
import static ru.isakovalexey.lunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiceServiceImpl implements VoiceService {

    private final VoiceRepository repository;

    public static final String EXCEPTION_VOTING_RESTRICTION = "exception.vote.restrictionVote";

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
        Voice voiceUser = repository.get(currentDate, userId);

        if (isNull(voiceUser)) {
            voiceUser = new Voice();
            return repository.save(voiceUser, restaurantId, userId);

        } else if (VoiceUtil.checkingTimeForSecondVote()) {
            return repository.save(voiceUser, restaurantId, userId);

        } else {
            throw new ApplicationException(EXCEPTION_VOTING_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, VoiceUtil.getTime().toString());
        }
    }
}
