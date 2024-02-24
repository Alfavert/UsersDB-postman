package Usersdbgradle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UsersDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String FullName;
    private String email;
    private String password;

    public UsersDB() {
    }

    public UsersDB(String fullName, String email, String password) {
        this.FullName = fullName;
        this.email = email;
        this.password = password;
    }
}