package ru.isakovalexey.lunch.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.ALL_WITH_VOICES_BY_DATE, query = "SELECT r.id, r.name, r.registered, COUNT(v) FROM Restaurant r LEFT JOIN r.voices v ON v.date=:date GROUP BY r.id, r.name ORDER BY r.name"),
        @NamedQuery(name =Restaurant.ALL_WITH_MEALS_BY_DATE, query ="SELECT DISTINCT r FROM Restaurant r left join fetch r.meals m where m.date=:date"),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_WITH_VOICES_BY_DATE = "Restaurant.allWithVoicesByDate";
    public static final String ALL_WITH_MEALS_BY_DATE = "Restaurant.allWithMealsByDate";
    public static final String ALL = "Restaurant.all";

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Voice> voices;

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

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
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
