package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.model.Voice;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by user on 17.06.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaVoiceRepositoryImpl implements VoiceRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Voice save(Voice voice, int restaurantId, int userId) {
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
    public Voice get(Date dateVoice, int userId) {
        Voice voice;
        try {
            voice = em.createNamedQuery(Voice.GET_VOICE_DATE, Voice.class)
                    .setParameter("userId", userId)
                    .setParameter("dateVoice", dateVoice)
                    .getSingleResult();
        } catch (NoResultException nre) {
            voice = null;
        }
        return voice;
    }
}
