package ru.mic;

import ru.mic.model.Restaurant;
import ru.mic.to.RestaurantWithVotes;

import java.util.Arrays;
import java.util.List;

import static ru.mic.model.AbstractEntity.START_SEQ;

public class RestsTestData {
    public static final int REST_ID = START_SEQ + 3;

    public static final Restaurant rest1 = new Restaurant(REST_ID, "rest1", "street One");
    public static final Restaurant rest2 = new Restaurant(REST_ID + 1, "rest2", "street Two");
    public static final List<Restaurant> RESTS = Arrays.asList(rest1, rest2);

    public static final RestaurantWithVotes restWV1 = new RestaurantWithVotes(REST_ID, "rest1", "street One", 2);
    public static final RestaurantWithVotes restWV2 = new RestaurantWithVotes(REST_ID + 1, "rest2", "street Two", 0);
    public static final List<RestaurantWithVotes> RESTAURANT_WITH_VOTES = Arrays.asList(restWV1, restWV2);

}
