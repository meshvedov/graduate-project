package ru.mic;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.mic.model.Menu;
import ru.mic.web.json.JsonUtil;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.mic.model.AbstractEntity.START_SEQ;

public class MenuTestData {
    public static final int MENU_ID = START_SEQ + 5;

    public static final Menu menu1_1 = new Menu(MENU_ID, "fish", 10);
    public static final Menu menu1_2 = new Menu(MENU_ID + 1, "meat", 20);
    public static final Menu menu1_3 = new Menu(MENU_ID + 2, "tea", 2);

    public static final Menu menu2_1 = new Menu(MENU_ID + 3, "snack", 5);
    public static final Menu menu2_2 = new Menu(MENU_ID + 4, "cake", 6);
    public static final Menu menu2_3 = new Menu(MENU_ID + 5, "coffee", 7);

    public static final List<Menu> MENUS_1 = Arrays.asList(menu1_1, menu1_2, menu1_3);
    public static final List<Menu> MENUS_2 = Arrays.asList(menu2_1, menu2_2, menu2_3);

    public static ResultMatcher contentJson(Menu... menus) {
        return content().json(JsonUtil.writeIgnoreProps(Arrays.asList(menus), ""));
    }
}
