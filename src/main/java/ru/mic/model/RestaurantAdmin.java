package ru.mic.model;

import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_user")
public class RestaurantAdmin extends AbstractEntity {

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    public RestaurantAdmin() { }

    public RestaurantAdmin(Integer id, Integer id1, int restaurantId, int userId) {
        super(id);
        this.id = id1;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
