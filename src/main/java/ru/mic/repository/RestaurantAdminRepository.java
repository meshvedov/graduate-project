package ru.mic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mic.model.RestaurantAdmin;

@Repository
public interface RestaurantAdminRepository extends JpaRepository<RestaurantAdmin, Integer> {

    @Query("select ra.restaurantId from RestaurantAdmin ra where ra.userId=:userId")
    int getRestaurantIdByUserId(@Param("userId") int userId);

}
