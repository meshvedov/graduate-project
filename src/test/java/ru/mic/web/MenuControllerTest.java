package ru.mic.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.mic.JpaUtil;
import ru.mic.TestUtil;
import ru.mic.model.Menu;
import ru.mic.web.json.JsonUtil;

import javax.annotation.PostConstruct;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mic.MenuTestData.*;
import static ru.mic.RestsTestData.REST_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MenuControllerTest {

    private static final String URL = "/rest/restaurants/";

    private MockMvc mockMvc;

    @Autowired
    private CacheManager cacheManager;
    @Autowired(required = false)
    private JpaUtil jpaUtil;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Before
    public void setUp() {
        cacheManager.getCache("menus").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }
    @Test
    public void listMenus() throws Exception {
        mockMvc.perform(get(URL +"/100003/menus"))
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

    }

    @Test
    public void deleteMenu() throws Exception {
    }

}