package ru.isakovalexey.lunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by user on 29.05.2017.
 */
@NamedQueries({@NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";

    @NotNull
    @Column(name = "vote")
    private Integer vote;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    public Restaurant() {
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
