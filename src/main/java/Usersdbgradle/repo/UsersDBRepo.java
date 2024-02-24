package Usersdbgradle.repo;

import Usersdbgradle.models.UsersDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;

public interface UsersDBRepo extends CrudRepository<UsersDB, Long> {

}