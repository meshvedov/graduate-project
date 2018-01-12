package ru.mic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
public class Vote extends AbstractEntity {

    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private Integer restaurantId;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @Column(name = "time", nullable = false)
    private LocalDateTime voteTime;

    public Vote() {}

    public Vote(@NotNull Integer restaurantId, @NotNull Integer userId) {
        this(null, restaurantId, userId, LocalDateTime.now());
    }

    public Vote(Integer id, @NotNull Integer restaurantId, @NotNull Integer userId, LocalDateTime voteTime) {
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.voteTime = voteTime;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }
}
