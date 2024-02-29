package Usersdbgradle.service;

import Usersdbgradle.models.UsersDB3;
import Usersdbgradle.repo.UsersDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersDBRepo usersRepo;
    public UsersDB3 saveDetails(UsersDB3 user){ return usersRepo.save(user); }
    public List<UsersDB3> saveListDetails(List<UsersDB3> users){ return usersRepo.saveAll(users); }
    public String deleteStudent(long id){
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return "deleted" + id;
        } else {
            return "ID is not existing";
        }
    }
    public UsersDB3 updateDetails(UsersDB3 user){
        UsersDB3 updateUser = usersRepo.findById(user.getId()).orElse(null);
        if(updateUser!=null){
            updateUser.setEmail(user.getEmail());
            updateUser.setFullName(user.getFullName());
            updateUser.setPassword(user.getPassword());
            usersRepo.save(updateUser);
            return updateUser;
        }
        return null;
    }
    public UsersDB3 getUserDetailsById(long id){
        return usersRepo.findById(id).orElse(null);

    }
}
