package ru.isakovalexey.lunch.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Meal;
import ru.isakovalexey.lunch.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        Meal meal = em.find(Meal.class, id);
        return meal != null && meal.getRestaurant().getId() == restaurantId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int restaurantId, Date date) {
        return em.createNamedQuery(Meal.ALL_BY_DATE, Meal.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }
}
