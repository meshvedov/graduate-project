package ru.mic.service;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.mic.model.Menu;
import ru.mic.repository.MenuRepositoryJpa;

import java.util.Arrays;
import java.util.List;

import static ru.mic.MenuTestData.*;
import static ru.mic.RestsTestData.REST_ID;

public class MenuServiceImplTest extends AbstractServiceTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected MenuService service;

    @Autowired
    private MenuRepositoryJpa repositoryJpa;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("menus").clear();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMenusByRestaurantId() throws Exception {
        List<Menu> menus = service.getMenusByRestaurantId(100003);
        Assertions.assertThat(menus).usingElementComparatorIgnoringFields("restaurant").isEqualTo(MENUS_1);
    }

    @Test
    public void delete() throws Exception {
        Menu withRest = service.getMenuByIdWithRestaurant(MENU_ID);
        service.delete(MENU_ID, withRest.getRestaurant().getId());
        List<Menu> menus = service.getMenusByRestaurantId(REST_ID);
        Assertions.assertThat(menus).usingElementComparatorIgnoringFields("restaurant").isEqualTo(Arrays.asList(menu1_2, menu1_3));
    }

    @Test
    public void save() throws Exception {
        Menu menu = new Menu(null, "bread", 20);
        service.save(menu, REST_ID);
        Menu menu1 = service.getMenuByIdWithRestaurant(menu.getId());
        Assertions.assertThat(menu1).isEqualToIgnoringGivenFields(menu, "restaurant");
    }

    @Test
    public void update() throws Exception {
        Menu menu = service.getMenuByIdWithRestaurant(MENU_ID);
        menu.setPrice(100);
        service.update(menu, MENU_ID,  REST_ID);
        Menu menu1 = service.getMenuByIdWithRestaurant(MENU_ID);
        Assertions.assertThat(menu).isEqualToIgnoringGivenFields(menu1, "restaurant");
    }

}