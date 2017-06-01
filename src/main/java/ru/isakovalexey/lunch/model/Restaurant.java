package ru.isakovalexey.lunch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Created by user on 29.05.2017.
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class Restaurant extends NamedEntity {

    @NotNull
    @Column(name = "vote")
    private Integer vote;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Integer vote) {
        super(id, name);
        this.vote = vote;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "vote=" + vote +
                '}';
    }
}
