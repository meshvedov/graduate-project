package ru.mic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mic.model.Vote;
import ru.mic.service.VoteService;

@RestController
@RequestMapping("/rest/restaurants")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/{restId}/votes/{userId}")
    public ResponseEntity<Vote> createVote(@PathVariable("restId") int restId, @PathVariable("userId")int userId) {
        Vote saved = voteService.save(restId, userId);
        if (saved == null) {
            return new ResponseEntity<Vote>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Vote>(HttpStatus.OK);
    }
}
