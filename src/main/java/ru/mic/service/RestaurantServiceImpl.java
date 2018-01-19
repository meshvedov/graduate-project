package ru.mic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.mic.model.Restaurant;
import ru.mic.model.Vote;
import ru.mic.repository.RestaurantRepository;
import ru.mic.repository.VoteRepository;
import ru.mic.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Cacheable("rest")
    @Override
    public List<RestaurantWithVotes> getAll() {
        List<Restaurant> restaurants = repository.findAll();
        List<RestaurantWithVotes> list = restaurants.stream().map(this::createWithVotes).collect(Collectors.toList());
        return list;
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
