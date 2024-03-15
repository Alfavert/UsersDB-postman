package Usersdbgradle.service;

import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersDBRepo usersRepo;

    public List<UsersDB3> getDetails() {
        return usersRepo.findAll();
//        throw new RuntimeException("error");
    }

    public UsersDB3 saveDetails(UsersDB3 user) {
        return usersRepo.save(user);
    }

    public List<UsersDB3> saveListDetails(List<UsersDB3> users) {
        return usersRepo.saveAll(users);
    }

    public String deleteStudent(long id) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return "deleted" + id;
        } else {
            return "ID is not existing";
        }
    }

    public UsersDB3 updateDetails(UsersDB3 user) {
        UsersDB3 updateUser = usersRepo.findById(user.getId()).orElse(null);
        if (updateUser != null) {
            updateUser.setEmail(user.getEmail());
            updateUser.setFullName(user.getFullName());
            updateUser.setPassword(user.getPassword());
            usersRepo.save(updateUser);
            return updateUser;
        }
        return null;
    }

    public UsersDB3 getUserDetailsById(long id) {
        return usersRepo.findById(id).orElse(null);

    }

    public List<UsersDB3> readFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<UsersDB3>> typeReference = new TypeReference<List<UsersDB3>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/user-data.json");

        try {
            List<UsersDB3> users = mapper.readValue(inputStream, typeReference);
            for (UsersDB3 user : users) {
                Optional<UsersDB3> existingUser = usersRepo.findById(user.getId());
                if (existingUser.isPresent()) {
                    System.out.println("User with id " + user.getId() + " already exists. Skipping save.");
                } else {
                    usersRepo.save(user);
                    System.out.println("User with id " + user.getId() + " saved!");
                }
            }
            System.out.println("Users Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
        return usersRepo.findAll();
    }
}
