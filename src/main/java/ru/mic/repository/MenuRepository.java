package ru.mic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mic.model.Menu;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getMenusByRestaurantId(@Param("restaurantId") int restId);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restId")
//    void delete(@Param("id") int id, @Param("restId") int restId);

    @Transactional
    void deleteMenuByIdAndRestaurantId(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restId")
    Menu getMenuByIdAndRestaurantId(@Param("id") int id, @Param("restId") int restId);

    @Transactional
    @Override
    Menu save(Menu menu);

    @Query("select m from Menu m join fetch m.restaurant where m.id=:id")
    Menu getMenuByIdWithRestaurant(@Param("id") int id);

}
