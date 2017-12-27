package ru.mic.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
public class Menu extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Menu () {
    }

    public Menu(@NotNull String name, @NotNull int price) {
        this.name = name;
        this.price = price;
    }

    public Menu(Integer id, @NotNull String name, @NotNull int price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
