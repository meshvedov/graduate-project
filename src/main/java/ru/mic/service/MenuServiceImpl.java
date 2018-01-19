package ru.mic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.mic.model.Menu;
import ru.mic.repository.MenuRepository;
import ru.mic.repository.RestaurantRepository;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

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

    @Cacheable("menus")
    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        return repository.getMenusByRestaurantId(restaurantId);
    }


    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id, int restaurantId) {
        repository.deleteMenuByIdAndRestaurantId(id, restaurantId);
    }


    @CacheEvict(value = "menus", allEntries = true)
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew())
            return null;
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
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
