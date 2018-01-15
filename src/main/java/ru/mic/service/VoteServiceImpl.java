package ru.mic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mic.model.Vote;
import ru.mic.repository.VoteRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Service
public class VoteServiceImpl implements VoteService {

    private static int TIME;

    @Autowired
    private VoteRepository repository;

    public void setTIME(int TIME) {
        VoteServiceImpl.TIME = TIME;
    }

    @Override
    public Vote save(int restId, int userId) {
        Vote vote = repository.getByUserId(userId);
        if (vote == null) {
            vote = new Vote(restId, userId);
            repository.save(vote);
            return vote;
        } else {
            LocalDateTime ldt = vote.getVoteTime();
            LocalDateTime todayTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(TIME, 00));
            if (ldt.isBefore(todayTime)) {
                vote.setVoteTime(LocalDateTime.now());
                repository.save(vote);
                return vote;
            }
        }
        return null;
    }
}
