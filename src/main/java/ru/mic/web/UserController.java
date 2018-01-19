package ru.mic.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mic.model.Menu;
import ru.mic.repository.MenuRepository;
import ru.mic.repository.RestaurantAdminRepository;
import ru.mic.repository.UserRepository;
import ru.mic.to.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantAdminRepository adminRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> listAll() {
        List<UserDTO> list = userRepository.getUsers().stream().map(user -> new UserDTO(user.getId(), user.getName(), user.getRoles())).collect(Collectors.toList());

        logger.info("listUsers: {}", list);
        return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> menuList(@PathVariable("id") int userId) {
        int restaurantId = adminRepository.getRestaurantIdByUserId(userId);
        List<Menu> menus = menuRepository.getMenusByRestaurantId(restaurantId);

        logger.info("menuList: {}", menus);
        return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
    }


}
