package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.model.Voice;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Voice.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
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

    @Override
    public List<Voice> getAll(int restaurantId) {
        return em.createNamedQuery(Voice.ALL, Voice.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    @Transactional
    public Voice voice(int restaurantId, boolean voice, int userId) {
        Voice voiceUser = null;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date currentDate = new Date();
        Date beforeDate = cal.getTime();

        if (currentDate.getTime() < beforeDate.getTime()) {
            voiceUser = get(currentDate, userId);
            if (voiceUser != null) {
                voiceUser.setRegistered(currentDate);
                save(voiceUser, restaurantId, userId);
            } else {
                voiceUser = new Voice();
                save(voiceUser, restaurantId, userId);
            }
        }

        return voiceUser;
    }
}
