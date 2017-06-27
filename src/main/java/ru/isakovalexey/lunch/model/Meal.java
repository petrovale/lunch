package ru.isakovalexey.lunch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import ru.isakovalexey.lunch.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Meal.ALL, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.description DESC "),
        @NamedQuery(name = Meal.ALL_BY_DATE, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId AND m.date=:date ORDER BY m.description DESC "),

})
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String ALL = "Meal.all";
    public static final String ALL_BY_DATE = "Meal.allByDate";

    @Column(name = "date")
    @NotNull(groups = {View.ValidatedREST.class, Default.class})
    private Date date = new Date();

    @Column(name = "description")
    @NotBlank(groups = {View.ValidatedREST.class, Default.class})
    @SafeHtml(groups = {View.ValidatedREST.class})
    private String description;

    @Column(name="restaurant_id", insertable=false, updatable=false)
    private Integer restaurantId;

    //@Column(name = "price", nullable= false, precision=7, scale=2)    // Creates the database field with this size.
    //@Digits(integer=9, fraction=2)
    @Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
    @NotNull(groups = {View.ValidatedREST.class, Default.class})
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, BigDecimal price) {
        this(null , description, price, new Date());
    }

    public Meal(Integer id, String description, BigDecimal price, Date date) {
        super(id);
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + getId() +
                "date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
