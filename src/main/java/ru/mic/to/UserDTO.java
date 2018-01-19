package ru.mic.to;

import org.springframework.util.comparator.Comparators;
import ru.mic.model.Role;

import java.util.*;

public class UserDTO {

    private Integer id;

    private String name;

    private Set<Role> roles;

    public UserDTO() {}

    public UserDTO(Integer id, String name, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public UserDTO(Integer id, String name, Role role, Role... roles) {
        this.id = id;
        this.name = name;
        this.roles = EnumSet.of(role, roles);
//        Set<Role> sorted = new TreeSet<>(Comparator.reverseOrder());
//        sorted.addAll(EnumSet.of(role, roles));
//        this.roles = sorted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
