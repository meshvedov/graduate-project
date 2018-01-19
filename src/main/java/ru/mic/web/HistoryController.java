package ru.mic.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mic.model.History;
import ru.mic.repository.HistoryRepository;

import javax.persistence.Cacheable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<History>> getHistories(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        List<History> list = historyRepository.getHistoriesByDate(localDate);

        logger.info("getHistories {} for date {}", list, date);
        return new ResponseEntity<List<History>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{date}/{restName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<History> getHistory(@PathVariable String date, @PathVariable String restName) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        History history = historyRepository.getHistoryByDateAndRestaurantName(localDate, restName);

        logger.info("getHistory: {} for restaurant name {}", history, restName);
        return new ResponseEntity<History>(history, HttpStatus.OK);
    }
}
