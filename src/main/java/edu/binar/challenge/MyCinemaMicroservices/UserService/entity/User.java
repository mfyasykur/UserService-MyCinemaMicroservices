package edu.binar.challenge.MyCinemaMicroservices.UserService.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long userId;

        @Column(name = "name", length = 64, nullable = false)
        private String name;

        @Column(name = "username", length = 64, nullable = false)
        @NotBlank
        private String username;

        @Column(name = "email", length = 64, nullable = false)
        @NotBlank
        @Email
        private String email;

        @Column(name = "password", length = 64, nullable = false)
        @NotBlank
        private String password;

        @Column(name = "phone", length = 16, nullable = false)
        private String phone;

        public enum ERole {
                ROLE_USER,
                ROLE_ADMIN
        }

        @Enumerated
        @Column(name = "role")
        private ERole role;

        public User(String requestName, String requestUsername, String requestEmail, String requestPassword, String requestPhone) {
                name = requestName;
                username = requestUsername;
                email = requestEmail;
                password = requestPassword;
                phone = requestPhone;
        }
}
