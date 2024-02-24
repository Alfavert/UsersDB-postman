package Usersdbgradle.repo;

import Usersdbgradle.models.UsersDB;
import org.springframework.data.repository.CrudRepository;

public interface UsersDBRepo extends CrudRepository<UsersDB, Long> {

}