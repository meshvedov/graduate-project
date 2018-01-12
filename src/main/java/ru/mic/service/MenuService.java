package ru.mic.service;

import ru.mic.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenusByRestaurantId(int restaurantId);

    void delete(int id, int restaurantId);

    Menu save(Menu menu, int restaurantId);

    Menu update(Menu menu, int id, int restaurantId);

    Menu getOne(int id, int restaurantId);

    Menu getMenuByIdWithRestaurant(int id);
}
