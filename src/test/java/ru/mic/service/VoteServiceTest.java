package ru.mic.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import ru.mic.model.Role;
import ru.mic.model.User;
import ru.mic.model.Vote;
import ru.mic.repository.UserRepository;
import ru.mic.repository.VoteRepository;

import java.time.LocalTime;

import static ru.mic.RestsTestData.REST_ID;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteServiceImpl service;
    @Autowired
    private VoteRepository repo;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() throws Exception {
        User user = new User(null, "qqq", "qqq", Role.ROLE_USER);
        userRepository.save(user);
        Vote vote = service.save(REST_ID, user.getId());
        Vote vote1 = repo.findOne(Example.of(vote)).get();
        Assertions.assertThat(vote).isEqualTo(vote1);
    }

    @Test
    public void saveVoteRepeated() throws Exception {
        int hour = LocalTime.now().getHour();
        if (hour == 23)
            service.setTIME(23);
        else service.setTIME(hour + 1);

        User user = new User(null, "qqq", "qqq", Role.ROLE_USER);
        userRepository.save(user);
        Vote vote = service.save(REST_ID, user.getId());
        Vote voteRepeat = service.save(REST_ID, user.getId());

        Vote vote1 = repo.getByUserId(user.getId());
        Assertions.assertThat(voteRepeat).isEqualTo(vote1);
    }

    @Test
    public void saveVoteRepeatedAfterTime() throws Exception {
        int hour = LocalTime.now().getHour();
        if (hour == 0)
            service.setTIME(0);
        else service.setTIME(hour - 1);

        User user = new User(null, "qqq", "qqq", Role.ROLE_USER);
        userRepository.save(user);
        Vote vote = service.save(REST_ID, user.getId());
        Vote voteRepeat = service.save(REST_ID, user.getId());

        Vote vote1 = repo.getByUserId(user.getId());
        Assertions.assertThat(voteRepeat).isNull();
    }
}