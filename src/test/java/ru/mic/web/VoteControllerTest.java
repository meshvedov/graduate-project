package ru.mic.web;

import org.assertj.core.api.Assertions;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mic.TestUtil;
import ru.mic.model.Role;
import ru.mic.model.User;
import ru.mic.model.Vote;
import ru.mic.repository.UserRepository;
import ru.mic.repository.VoteRepository;
import ru.mic.web.json.JsonUtil;

import java.util.EnumSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static ru.mic.RestsTestData.REST_ID;
import static ru.mic.model.AbstractEntity.START_SEQ;

public class VoteControllerTest extends AbstractControllerTest {

    private static final Integer USER_ID = START_SEQ;
    private static final String URL = "/rest/restaurants";

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void createVote() throws Exception {
        User user = userRepository.save(new User("user3", "1", EnumSet.of(Role.ROLE_USER)));

        Vote vote = new Vote(REST_ID, USER_ID);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/" + REST_ID + "/votes/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(vote)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Vote returned = TestUtil.readFromJson(actions, Vote.class);

        vote.setId(returned.getId());
        Assertions.assertThat(returned).isEqualTo(vote);

    }

}