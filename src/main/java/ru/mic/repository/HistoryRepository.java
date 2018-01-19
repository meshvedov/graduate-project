package ru.mic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mic.model.History;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

    History getHistoryByDateAndRestaurantName(LocalDate date, String restaurantName);

    List<History> getHistoriesByDate(@Param("date")LocalDate date);

}
