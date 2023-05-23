package com.estore.ecommercespring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;

@Entity()
@Table(name ="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
    generator = "user_id")
    @SequenceGenerator(
            name="user_id",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String salt;

    public User(String firstName,
                String lastName,
                String password,
                String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);

    }
    public boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
}
