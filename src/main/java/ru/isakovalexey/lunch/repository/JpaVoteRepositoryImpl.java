package ru.isakovalexey.lunch.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.isakovalexey.lunch.model.Restaurant;
import ru.isakovalexey.lunch.model.User;
import ru.isakovalexey.lunch.model.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;
import static ru.isakovalexey.lunch.util.VoteUtil.checkingTimeForSecondVote;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Date date, int restaurantId, int userId) {
        Vote vote = get(date, userId);
        if (isNull(vote)) {
            vote = new Vote();
        } else {
            checkingTimeForSecondVote();
        }
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        vote.setUser(em.getReference(User.class, userId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public Vote get(Date date, int userId) {
        List<Vote> voice = em.createNamedQuery(Vote.GET_BY_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .getResultList();
        return DataAccessUtils.singleResult(voice);
    }
}
