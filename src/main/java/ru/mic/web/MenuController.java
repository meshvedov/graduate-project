package ru.mic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mic.model.Menu;
import ru.mic.model.Restaurant;
import ru.mic.repository.VoteRepository;
import ru.mic.service.MenuService;
import ru.mic.service.RestaurantService;
import ru.mic.to.RestaurantWithVotes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/restaurants")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/{restId}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> listMenus(@PathVariable("restId") int restId) {
        List<Menu> menus = menuService.getMenusByRestaurantId(restId);
        return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
    }

    @PostMapping(value = "/{restId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@PathVariable("restId") int restId, @RequestBody Menu menu) {
        Menu created = menuService.save(menu, restId);
        return new ResponseEntity<Menu>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{restId}/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> updateMenu(@PathVariable("restId") int restId, @PathVariable("id") int id, @RequestBody Menu menu) {
        Menu updated = menuService.update(menu, id, restId);
        return new ResponseEntity<Menu>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{restId}/menu/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable("restId") int restId, @PathVariable("id") int id) {
        menuService.delete(id, restId);
        return new ResponseEntity<Menu>(HttpStatus.NO_CONTENT);
    }

}

