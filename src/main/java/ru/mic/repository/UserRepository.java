package ru.mic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mic.model.User;
import ru.mic.to.UserDTO;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("select new ru.mic.to.UserDTO(u.id, u.name, u.roles) from User u")
//    List<UserDTO> getUsersWithNameAndRole();

    @Query("select distinct u from User u join fetch u.roles")
    List<User> getUsers();
}
