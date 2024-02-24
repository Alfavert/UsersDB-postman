package Usersdbgradle.controllers;
import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
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

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDB3> all() {
        return usersRepository.findAll();
    }

    @GetMapping("/")
    public String usersMain(Model model) {
        Iterable<UsersDB3> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-user")
    public String usersAdd(Model model) {
        return "user-add";
    }

    @PostMapping("/add-user")
    public String userPostAdd(@RequestParam String FullName, @RequestParam String email, @RequestParam String password, Model model) {
        UsersDB3 user = new UsersDB3(FullName, email, password);
        usersRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String UsersDetails(@PathVariable(value = "id") long id, Model model) {
        if (!usersRepository.existsById(id)) {
            return "redirect:/";
        } else {
            Optional<UsersDB3> user = usersRepository.findById(id);
            ArrayList<UsersDB3> res = new ArrayList<>();
            user.ifPresent(res::add);
            model.addAttribute("user", res);
            return "user-details";
        }
    }

    @GetMapping("/{id}/edit")
    public String UsersEdit(@PathVariable(value = "id") long id, Model model) {
        if (!usersRepository.existsById(id)) {
            return "redirect:/";
        } else {
            Optional<UsersDB3> user = usersRepository.findById(id);
            ArrayList<UsersDB3> res = new ArrayList<>();
            user.ifPresent(res::add);
            model.addAttribute("user", res);
            return "user-edit";
        }
    }

    @PostMapping("/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") long id,@RequestParam String FullName, @RequestParam String email, @RequestParam String password, Model model) {
        UsersDB3 user = usersRepository.findById(id).orElseThrow();
        user.setEmail(email);
        user.setFullName(FullName);
        user.setPassword(password);
        usersRepository.save(user);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String userDelete(@PathVariable(value = "id") long id, Model model) {
        UsersDB3 user = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(user);
        return "redirect:/";
    }
}