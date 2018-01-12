package ru.mic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "address", nullable = false)
    @NotNull
    private String address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "restaurant")
    protected List<Menu> menuList;

    public Restaurant () {
    }

    public Restaurant(Integer id, @NotNull String name, @NotNull String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", menuList=" + menuList +
                ", id=" + id +
                '}';
    }
}
