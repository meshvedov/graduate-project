package ru.mic.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "history")
public class History extends AbstractEntity {

    @Column(name = "name")
    private String restaurantName;

    @Column(name = "votes")
//    @Size(min = 0)
    private int votes;

    @Column(name = "date")
    private LocalDate date;

    public History () {}

    public History(String restaurantName, @Size(min = 0) int votes, LocalDate date) {
        this.restaurantName = restaurantName;
        this.votes = votes;
        this.date = date;
    }

    public History(Integer id, String restaurantName, @Size(min = 0) int votes, LocalDate date) {
        super(id);
        this.restaurantName = restaurantName;
        this.votes = votes;
        this.date = date;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTime) {
        this.date = dateTime;
    }
}
