package ru.mic.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.mic.service.MenuService;
import ru.mic.utils.JpaUtil;
import ru.mic.TestUtil;
import ru.mic.model.Menu;
import ru.mic.web.json.JsonUtil;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mic.MenuTestData.*;
import static ru.mic.RestsTestData.REST_ID;

public class MenuControllerTest extends AbstractControllerTest {

    private static final String URL = "/rest/restaurants/";

    @Autowired
    private MenuService menuService;

    @Test
    public void listMenus() throws Exception {
        mockMvc.perform(get(URL + "/100003/menus"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(menu1_1, menu1_2, menu1_3));
    }

    @Test
    public void createMenu() throws Exception {
        Menu created = new Menu(null, "bacon", 50);
        ResultActions actions = mockMvc.perform(post(URL + REST_ID + "/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        Menu returned = TestUtil.readFromJson(actions, Menu.class);
        created.setId(returned.getId());
        assertMatch(created, returned);
    }

    @Test
    public void updateMenu() throws Exception {
        Menu menu = menuService.getOne(MENU_ID, REST_ID);
        menu.setPrice(100);
        ResultActions actions = mockMvc.perform(put(URL + REST_ID + "/menu/" + menu.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menu)))
                .andExpect(status().isOk());

        Menu returned = TestUtil.readFromJson(actions, Menu.class);
        assertMatch(menu, returned);
    }

    @Test
    public void deleteMenu() throws Exception {
        ResultActions actions = mockMvc.perform(delete(URL + REST_ID + "/menu/" + MENU_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(menuService.getMenusByRestaurantId(REST_ID), menu1_2, menu1_3);
    }

}