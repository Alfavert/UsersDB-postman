package Usersdbgradle.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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