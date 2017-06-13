package ru.isakovalexey.lunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by user on 30.05.2017.
 */
@NamedQueries({
    @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
    @NamedQuery(name = Meal.ALL, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.dateTime DESC "),
})
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String ALL = "Meal.all";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description")
    @NotBlank
    private String description;

    //@Column(name = "price", nullable= false, precision=7, scale=2)    // Creates the database field with this size.
    //@Digits(integer=9, fraction=2)
    @Column(name="price", columnDefinition="Decimal(10,2) default '100.00'")
    @NotNull
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, BigDecimal price) {
        this(null ,dateTime, description, price);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, BigDecimal price) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isNew() {
        return getId() == null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + getId() +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
