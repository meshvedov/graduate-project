package ru.mic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mic.model.Vote;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote getByUserId(@Param("userId") int userId);

    @Query("select count(v.restaurantId) from Vote v where v.restaurantId=:restaurantId")
    int getCountVotesByRestaurantId(@Param("restaurantId") int restId);

    List<Vote> getVotesByRestaurantId(@Param("restaurantId") int restId);
}
