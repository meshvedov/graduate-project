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

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantWithVotes>> listRests() {
        List<Restaurant> restaurants = restaurantService.getAll();
        List<RestaurantWithVotes> list = restaurants.stream().map(this::createWithVotes).collect(Collectors.toList());
        return new ResponseEntity<List<RestaurantWithVotes>>(list, HttpStatus.OK);
    }

    private RestaurantWithVotes createWithVotes(Restaurant rest) {
        int votes = voteRepository.getCountVotesByRestaurantId(rest.getId());
        List<Vote> list = voteRepository.getVotesByRestaurantId(rest.getId());
        LocalDate localDateNow = LocalDate.now();
        int count = 0;
        for (Vote vote : list) {
            if (vote.getVoteTime().toLocalDate().isEqual(localDateNow)) {
                count++;
            }
        }
        return new RestaurantWithVotes(rest.getId(), rest.getName(), rest.getAddress(), count);
    }
}
