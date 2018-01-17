package ru.mic.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mic.TestUtil;
import ru.mic.model.Menu;
import ru.mic.model.Role;
import ru.mic.to.UserDTO;
import ru.mic.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.junit.Assert.*;
import static org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.mic.MenuTestData.MENU_ID;
import static ru.mic.model.AbstractEntity.START_SEQ;

public class UserControllerTest extends AbstractControllerTest {

    private static final String URL = "/users";
    private static final Integer USER_ID = START_SEQ;

    @Test
    public void listAll() throws Exception {
        UserDTO user1 = new UserDTO(USER_ID, "admin", Role.ROLE_ADMIN, Role.ROLE_USER);
        UserDTO user2 = new UserDTO(USER_ID + 1, "user1", EnumSet.of(Role.ROLE_USER));
        UserDTO user3 = new UserDTO(USER_ID + 2, "user2", EnumSet.of(Role.ROLE_USER));

        ResultActions actions = mockMvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(user1, user2, user3));
    }

    @Test
    public void menuList() throws Exception {
        Menu menu1 = new Menu(MENU_ID, "fish", 10);
        Menu menu2 = new Menu(MENU_ID+1, "meat", 20);
        Menu menu3 = new Menu(MENU_ID+2, "tea", 2);

        ResultActions actions = mockMvc.perform(get(URL + "/"+USER_ID + "/menus").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(menu1, menu2, menu3));
    }

}