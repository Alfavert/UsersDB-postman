package Usersdbgradle.repo;

import Usersdbgradle.models.UsersDB3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDBRepo extends JpaRepository<UsersDB3, Long> {

}