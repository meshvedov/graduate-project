package ru.mic.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mic.TestUtil;
import ru.mic.model.Restaurant;
import ru.mic.to.RestaurantWithVotes;
import ru.mic.web.json.JsonUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mic.MenuTestData.contentJson;
import static ru.mic.RestsTestData.REST_ID;

public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String URL = "/rest/restaurants";

    @Test
    public void listRests() throws Exception {
        RestaurantWithVotes restaurantWithVotes1 = new RestaurantWithVotes(REST_ID, "rest1", "street One", 2);
        RestaurantWithVotes restaurantWithVotes2 = new RestaurantWithVotes(REST_ID + 1, "rest2", "street Two", 0);
        String expected = JsonUtil.writeIgnoreProps(Arrays.asList(restaurantWithVotes1, restaurantWithVotes2));
        ResultActions actions = mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(restaurantWithVotes1, restaurantWithVotes2));
    }

}