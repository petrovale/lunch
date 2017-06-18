package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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
        return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
    }

    @Override
    @Transactional
    public List<Restaurant> getAllVoice() {
        List<Object[]> objects = em.createNamedQuery(Restaurant.ALL_VOICES, Object[].class)
                .getResultList();
        List<Restaurant> restaurants = new ArrayList<>();

        for (Object[] object : objects) {
            int id = ((Number) object[0]).intValue();
            String name = (String) object[1];
            Date date = (Date) object[2];
            int vote = ((Number) object[3]).intValue();
            restaurants.add(new Restaurant(id, name, vote, date));
        }

        return restaurants;
    }


}
