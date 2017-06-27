package ru.isakovalexey.lunch.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.model.Voice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;
import static ru.isakovalexey.lunch.util.VoiceUtil.checkingTimeForSecondVote;

@Repository
@Transactional(readOnly = true)
public class JpaVoiceRepositoryImpl implements VoiceRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Voice save(Date date, int restaurantId, int userId) {
        Voice voice = get(date, userId);
        if (isNull(voice)) {
            voice = new Voice();
        } else {
            checkingTimeForSecondVote();
        }
        voice.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        voice.setUser(em.getReference(User.class, userId));
        if (voice.isNew()) {
            em.persist(voice);
            return  voice;
        } else {
            return em.merge(voice);
        }
    }

    @Override
    public Voice get(Date date, int userId) {
        List<Voice> voice = em.createNamedQuery(Voice.GET_VOICE_DATE, Voice.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .getResultList();
        return DataAccessUtils.singleResult(voice);
    }
}
