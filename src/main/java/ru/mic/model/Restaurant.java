package ru.mic.model;

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

    @Column(name = "admin_id")
    private int admin_id;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "restaurant")
    protected List<Menu> menuList;

    public Restaurant () {
    }

    public Restaurant(Integer id, @NotNull String name, @NotNull String address, int admin_id) {
        super(id);
        this.name = name;
        this.address = address;
        this.admin_id = admin_id;
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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", admin_id=" + admin_id +
                ", menuList=" + menuList +
                ", id=" + id +
                '}';
    }
}
