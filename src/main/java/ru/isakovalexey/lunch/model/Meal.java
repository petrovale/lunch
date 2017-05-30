package ru.isakovalexey.lunch.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by user on 30.05.2017.
 */
public class Meal extends BaseEntity {
    private LocalDateTime dateTime;

    private String description;

    //@Column(name = "price" nullable= false, precision=7, scale=2)    // Creates the database field with this size.
    //@Digits(integer=9, fraction=2)
    //@Column(name="price", columnDefinition="Decimal(10,2) default '100.00'")
    private BigDecimal price;

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
        return id == null;
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
