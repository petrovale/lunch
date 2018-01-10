package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;

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
    public List<Object[]> getAllWithVotesByDate(Date dateVote) {
        return em.createNamedQuery(Restaurant.ALL_WITH_VOTES_BY_DATE, Object[].class)
                .setParameter("date", dateVote)
                .getResultList();
    }

    @Override
    public List<Restaurant> getAllWithMealsByDate(LocalDate date) {
        return em.createNamedQuery(Restaurant.ALL_WITH_MEALS_BY_DATE, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }

}
