package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.to.RestaurantTo;
import ru.isakovalexey.lunch.util.RestaurantUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE).setParameter("id",id).executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }

    @Override
    @Transactional
    public List<RestaurantTo> getAllWithVoicesByDate(Date dateVoice) {
        List<Object[]> objects = em.createNamedQuery(Restaurant.ALL_WITH_VOICES_BY_DATE, Object[].class)
                .setParameter("date", dateVoice)
                .getResultList();
        return RestaurantUtil.createWithVoice(objects);
    }

    @Override
    @Transactional
    public List<Restaurant> getAllWithMealsByDate(LocalDate date) {
        return em.createQuery("SELECT DISTINCT r FROM Restaurant r left join fetch r.meals m where m.date=:date", Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }

}
