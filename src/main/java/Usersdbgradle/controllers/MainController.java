package Usersdbgradle.controllers;
import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import Usersdbgradle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UsersDBRepo usersRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDB3> all() {
        return usersRepository.findAll();
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
}
