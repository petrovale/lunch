package ru.isakovalexey.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.ALL_WITH_VOTES_BY_DATE, query = "SELECT r.id, r.name, r.registered, COUNT(v) FROM Restaurant r LEFT JOIN r.votes v ON v.date=:date GROUP BY r.id, r.name ORDER BY r.name"),
        @NamedQuery(name =Restaurant.ALL_WITH_MEALS_BY_DATE, query ="SELECT DISTINCT r FROM Restaurant r left join fetch r.meals m where m.date=:date"),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_WITH_VOTES_BY_DATE = "Restaurant.allWithVotesByDate";
    public static final String ALL_WITH_MEALS_BY_DATE = "Restaurant.allWithMealsByDate";
    public static final String ALL = "Restaurant.all";

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonIgnore
    private List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @OrderBy("dateTime DESC")
//    @JsonIgnore
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getRegistered());
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        this(id, name, new Date());
    }

    public Restaurant(Integer id, String name, Date registered) {
        super(id, name);
        this.registered = registered;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + name +
                ", registered=" + registered +
                '}';
    }
}
