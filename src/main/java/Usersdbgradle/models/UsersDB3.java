package Usersdbgradle.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Data
@Table(name = "users")
public class UsersDB3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public UsersDB3() {
    }

    public UsersDB3(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UsersDB3(long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;

    }
}