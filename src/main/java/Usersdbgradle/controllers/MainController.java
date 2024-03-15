package Usersdbgradle.controllers;
import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import Usersdbgradle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MainController {

    @Autowired
    private UsersDBRepo usersRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDB3> getDetails() {
        return userService.getDetails();
    }

    @PostMapping("/api/addUser")
    public UsersDB3 postDetails(@RequestBody UsersDB3 user) {
        return userService.saveDetails(user);
    }

    @PostMapping("/api/addUserList")
    public List<UsersDB3> postDetails(@RequestBody List<UsersDB3> users) {
        return userService.saveListDetails(users);
    }

    @DeleteMapping("/api/deleteUser/{id}")
    public String deleteDetails(@PathVariable long id) {
        return userService.deleteStudent(id);
    }

    @PutMapping("/api/editUser")
    public UsersDB3 updateUserDetails(@RequestBody UsersDB3 user) {
        return userService.updateDetails(user);
    }
    @GetMapping(value = "/api/getUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDB3 fetchUserDetails(@PathVariable long id){
        return userService.getUserDetailsById(id);
    }
    @GetMapping(value = "/api/readFromJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDB3> readFromJson(){
        return userService.readFromJson();
    }
    @GetMapping("/api/allUsers")
    public List<UsersDB3> getAllUsers() {
        List<UsersDB3> users = userService.getDetails();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src\\main\\resources\\json\\allUsers.json"), users);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return users;
    }


}

