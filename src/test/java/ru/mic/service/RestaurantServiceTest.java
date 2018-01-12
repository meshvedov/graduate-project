package ru.mic.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mic.model.Restaurant;

import java.util.List;

import static org.junit.Assert.*;
import static ru.mic.RestsTestData.RESTS;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() throws Exception {
        List<Restaurant> restaurants = service.getAll();
        Assertions.assertThat(restaurants).usingElementComparatorIgnoringFields("menuList").isEqualTo(RESTS);
    }

}