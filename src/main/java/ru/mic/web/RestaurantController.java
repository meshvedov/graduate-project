package ru.mic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mic.model.Restaurant;
import ru.mic.model.Vote;
import ru.mic.repository.VoteRepository;
import ru.mic.service.RestaurantService;
import ru.mic.to.RestaurantWithVotes;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantWithVotes>> listRests() {
        List<RestaurantWithVotes> list = restaurantService.getAll();
        return new ResponseEntity<List<RestaurantWithVotes>>(list, HttpStatus.OK);
    }


}
