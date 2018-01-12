package ru.mic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mic.service.VoteService;

@RestController
@RequestMapping("/rest/restaurants")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/{restId}/votes/{userId}")
    public void createVote(@PathVariable("restId") int restId, @PathVariable("userId")int userId) {
        voteService.save(restId, userId);
    }
}
