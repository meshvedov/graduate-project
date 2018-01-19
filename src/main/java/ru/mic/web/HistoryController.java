package ru.mic.web;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<History>> getHistories(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        List<History> list = historyRepository.getHistoriesByDate(localDate);
//        List<History> list = historyRepository.findAll();
        return new ResponseEntity<List<History>>(list, HttpStatus.OK);
    }
}
