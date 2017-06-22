package ru.isakovalexey.lunch.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 29.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_VOICES, query = "SELECT r.id, r.name, r.registered, COUNT(v) FROM Restaurant r LEFT JOIN r.voices v GROUP BY r.id, r.name ORDER BY r.name"),
        @NamedQuery(name = Restaurant.ALL_VOICES_BY_Date, query = "SELECT r.id, r.name, r.registered, COUNT(v) FROM Restaurant r LEFT JOIN r.voices v ON v.dateVoice=:date GROUP BY r.id, r.name ORDER BY r.name"),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_VOICES = "Restaurant.allVoices";
    public static final String ALL_VOICES_BY_Date = "Restaurant.allVoicesByDate";

    @Column(name = "vote")
    private Integer vote = 0;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Voice> voices;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getVote(), r.getRegistered());
    }

    public Restaurant(String name) {
        this(null, name, 0);
    }

    public Restaurant(Integer id, String name, Integer vote) {
        this(id, name, vote, new Date());
    }

    public Restaurant(Integer id, String name, Integer vote, Date registered) {
        super(id, name);
        this.vote = vote;
        this.registered = registered;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + name +
                ", vote=" + vote +
                ", registered=" + registered +
                '}';
    }
}
