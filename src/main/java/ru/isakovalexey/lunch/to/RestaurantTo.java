package ru.isakovalexey.lunch.to;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RestaurantTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Integer vote;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime registered;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, Integer vote) {
        this(id, name, vote, LocalDateTime.now());
    }

    public RestaurantTo(Integer id, String name, Integer vote, LocalDateTime registered) {
        this.id = id;
        this.name = name;
        this.vote = vote;
        this.registered = registered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vote=" + vote +
                ", registered=" + registered +
                '}';
    }
}
