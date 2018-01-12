package ru.mic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mic.model.User;
import ru.mic.repository.UserRepository;
import ru.mic.to.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> listAll() {
        List<UserDTO> list = userRepository.getUsers().stream().map(user -> new UserDTO(user.getId(), user.getName(), user.getRoles())).collect(Collectors.toList());
        return list;
    }
}
