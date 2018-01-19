package ru.mic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mic.model.History;
import ru.mic.repository.HistoryRepository;
import ru.mic.service.RestaurantService;
import ru.mic.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private HistoryRepository historyRepository;

//    @Scheduled(cron = "* 0 0 * * *")
    @Transactional
    @Scheduled(fixedRate = 100_000)
    public void work() {
        log.info("Time is NOW {}", LocalDateTime.now());

        List<RestaurantWithVotes> restaurantWithVotes = restaurantService.getAll();
//        LocalDateTime date = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDate date = LocalDate.now();
        List<History> collect = restaurantWithVotes.stream().map(r -> new History(r.getName(), r.getVotes(), date)).collect(Collectors.toList());

        List<History> historyList = historyRepository.saveAll(collect);
        int i = 0;
    }

}
