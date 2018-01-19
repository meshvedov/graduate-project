package ru.mic.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mic.model.Restaurant;
import ru.mic.to.RestaurantWithVotes;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static ru.mic.RestsTestData.RESTAURANT_WITH_VOTES;
import static ru.mic.RestsTestData.RESTS;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() throws Exception {
        List<RestaurantWithVotes> restaurants = service.getAll();
//        Assertions.assertThat(restaurants).usingElementComparatorIgnoringFields("menuList").isEqualTo(RESTS);
        Assertions.assertThat(restaurants).usingElementComparatorIgnoringFields("id").isEqualTo(RESTAURANT_WITH_VOTES);
    }

}