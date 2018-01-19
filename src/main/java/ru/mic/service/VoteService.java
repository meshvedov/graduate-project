package ru.mic.service;

import ru.mic.model.Vote;

public interface VoteService {

    Vote save(int restId, int userId);

    void setTIME(int time);

}
