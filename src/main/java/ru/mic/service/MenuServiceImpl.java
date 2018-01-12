package ru.mic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mic.model.Menu;
import ru.mic.repository.MenuRepositoryJpa;
import ru.mic.repository.RestaurantRepository;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepositoryJpa repository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Menu getOne(int id, int restaurantId) {
        return repository.getMenuByIdAndRestaurantId(id, restaurantId);
    }

    @Override
    public Menu getMenuByIdWithRestaurant(int id) {
        return repository.getMenuByIdWithRestaurant(id);
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        return repository.getMenusByRestaurantId(restaurantId);
    }


    public void delete(int id, int restaurantId) {
        repository.deleteMenuByIdAndRestaurantId(id, restaurantId);
    }


    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew())
            return null;
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(menu);
    }

    @Override
    public Menu update(Menu menu, int id, int restaurantId) {
        if (menu.isNew())
            return null;
        Menu menuTemp = repository.getMenuByIdAndRestaurantId(id, restaurantId);
        menuTemp.setName(menu.getName());
        menuTemp.setPrice(menu.getPrice());
        repository.save(menuTemp);

        return menuTemp;
    }
}
